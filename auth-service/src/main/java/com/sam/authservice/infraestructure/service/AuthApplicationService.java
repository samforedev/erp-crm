package com.sam.authservice.infraestructure.service;

import com.sam.authservice.application.dto.LoginRequest;
import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.application.dto.TokenResponse;
import com.sam.authservice.application.dto.common.Error;
import com.sam.authservice.application.dto.common.Response;
import com.sam.authservice.application.mapper.UserDtoMapper;
import com.sam.authservice.application.service.IAuthApplicationService;
import com.sam.authservice.domain.enums.Status;
import com.sam.authservice.domain.model.User;
import com.sam.authservice.domain.port.IJwtServicePort;
import com.sam.authservice.domain.port.IUserRepositoryPort;
import com.sam.authservice.infraestructure.persistence.entity.RoleEntity;
import com.sam.authservice.infraestructure.persistence.mapper.RoleMapper;
import com.sam.authservice.infraestructure.persistence.repository.IRoleJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthApplicationService implements IAuthApplicationService {

    private final IUserRepositoryPort _userRepository;
    private final IRoleJpaRepository _roleRepository;
    private final RoleMapper _roleMapper;
    private final UserDtoMapper _authDtoMapper;
    private final PasswordEncoder _passwordEncoder;
    private final AuthenticationManager _authenticationManager;
    private final IJwtServicePort _jwtService;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Override
    @Transactional
    public Response<UUID> registerUser(RegisterRequest request) {
        try {
            Optional<User> userFound = _userRepository.findByEmail(request.email());
            if (userFound.isPresent())
                return Response.error(Error.of("already_exists", "The user already exists"));

            Optional<RoleEntity> roleEntity = _roleRepository.findByName(request.initialRoleName());
            if (roleEntity.isEmpty())
                return Response.error(Error.of("not_found", "The role does not exist"));

            User newUser = _authDtoMapper.toDomain(request);
            newUser.setRoles(Collections.singleton(_roleMapper.toDomain(roleEntity.get())));
            String hashedPassword = _passwordEncoder.encode(request.password());
            newUser.setPassword(hashedPassword);
            newUser.setStatus(Status.ACTIVE);
            User userRegister = _userRepository.save(newUser);

            return Response.success(userRegister.getId(), "User created successfully");

        } catch (Exception ex) {
            return Response.error(Error.of("Exception", ex.getMessage()));
        }
    }

    @Override
    public Response<TokenResponse> loginUser(LoginRequest request) {
        try {
            Authentication authentication = _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.usernameOrEmail(),
                            request.password()
                    )
            );
            String username = authentication.getName();
            User domainUser = _userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

            String jwt = _jwtService.generateToken(domainUser);
            long expirationSeconds = TimeUnit.MILLISECONDS.toSeconds(this.jwtExpiration);

            return Response.success(
                    new TokenResponse(jwt, "Bearer", expirationSeconds), "Login successful");

        } catch (Exception ex) {
            return Response.error(Error.of("Exception", ex.getMessage()));
        }
    }

    @Override
    public User getUserDetailsForToken(UUID userId) {
        return null;
    }
}
