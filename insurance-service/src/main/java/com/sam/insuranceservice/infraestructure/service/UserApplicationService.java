package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.Error;
import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.error.UserErrorCatalog;
import com.sam.insuranceservice.application.dto.user.GetByUsernameOrEmail;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;
import com.sam.insuranceservice.application.mapper.UserDtoMapper;
import com.sam.insuranceservice.application.service.IUserApplicationService;
import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserApplicationService implements IUserApplicationService {

    private final IUserRepositoryPort _userRepositoryPort;
    private final UserDtoMapper _userDtoMapper;

    @Override
    public Response<UserResponse> findUserByDocumentNumberOrEmail(GetByUsernameOrEmail request) {
        try {
            Optional<User> userFound = _userRepositoryPort.findByEmail(request.usernameOrEmail())
                    .or(() -> _userRepositoryPort.findByUsername(request.usernameOrEmail()));
            if (userFound.isEmpty())
                return Response.error(UserErrorCatalog.NOT_FOUND);

            UserResponse response = _userDtoMapper.toResponse(userFound.get());
            return Response.success(response, "User found successfully");

        } catch (Exception ex) {
            return Response.error(Error.of("Exception", ex.getMessage()));
        }
    }

    @Override
    public Response<List<UserMinimalResponse>> findAllUsers() {
        try {
            List<User> usersFound = _userRepositoryPort.findAll();
            if (usersFound.isEmpty())
                return Response.success(Collections.emptyList(), "Users not found");

            List<UserMinimalResponse> response = usersFound.stream()
                    .map(_userDtoMapper::toMinimalResponse)
                    .toList();
            return Response.success(response, "Users found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }
}
