package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.Error;
import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.error.UserErrorCatalog;
import com.sam.insuranceservice.application.dto.user.UserCreationRequest;
import com.sam.insuranceservice.application.mapper.UserDtoMapper;
import com.sam.insuranceservice.application.service.IUserApplicationService;
import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements IUserApplicationService {

    private final IUserRepositoryPort _userRepositoryPort;
    private final UserDtoMapper _userDtoMapper;

    @Override
    public Response<UUID> createUser(UserCreationRequest request) {
        try {
            Optional<User> userFound = _userRepositoryPort.findByDocumentNumber(request.documentNumber());
            if (userFound.isPresent())
                return Response.error(UserErrorCatalog.ALREADY_EXISTS);

            User userDomain = _userDtoMapper.toDomain(request);
            User newUser = _userRepositoryPort.save(userDomain);
            if (newUser == null)
                return Response.error(UserErrorCatalog.ERROR_CREATING);

            return Response.success(newUser.getId(), "User has been created successfully");

        } catch (Exception ex) {
            return Response.error(Error.of("Exception", ex.getMessage()));
        }
    }
}
