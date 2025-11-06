package com.sam.authservice.infraestructure.service;

import com.sam.authservice.application.dto.LoginRequest;
import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.application.dto.TokenResponse;
import com.sam.authservice.application.mapper.AuthDtoMapper;
import com.sam.authservice.application.service.IAuthApplicationService;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthApplicationService implements IAuthApplicationService {

    private final IUserRepositoryPort _userRepository;
    private final IRoleJpaRepository _roleRepository;
    private final RoleMapper _roleMapper;
    private final AuthDtoMapper _authDtoMapper;
    private final PasswordEncoder _passwordEncoder;
    private final AuthenticationManager _authenticationManager;
    private final IJwtServicePort _jwtService;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Override
    @Transactional
    public UUID registerUser(RegisterRequest request) {
        User newUser = _authDtoMapper.toDomain(request);
        RoleEntity roleEntity = _roleRepository.findByName(request.initialRoleName())
                .orElseThrow(() -> new RuntimeException("ROLE_NOT_FOUND"));

        newUser.setRoles(Collections.singleton(_roleMapper.toDomain(roleEntity)));
        String hashedPassword = _passwordEncoder.encode(request.password());
        newUser.setPassword(hashedPassword);

        User userRegister = _userRepository.save(newUser);
        return userRegister.getId();
    }

    @Override
    public TokenResponse loginUser(LoginRequest request) {
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

        return new TokenResponse(jwt, "Bearer", expirationSeconds);
    }

    @Override
    public User getUserDetailsForToken(UUID userId) {
        return _userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
