package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.ChangeStatusResponse;
import com.sam.insuranceservice.application.dto.common.EntityIdResponse;
import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.application.dto.common.StatusCommons;
import com.sam.insuranceservice.application.dto.common.response.Error;
import com.sam.insuranceservice.application.dto.common.response.Response;
import com.sam.insuranceservice.application.dto.error.UserErrorCatalog;
import com.sam.insuranceservice.application.dto.user.*;
import com.sam.insuranceservice.application.mapper.UserDtoMapper;
import com.sam.insuranceservice.application.service.IUserApplicationService;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserApplicationService implements IUserApplicationService {

    private final IUserRepositoryPort _userRepositoryPort;
    private final UserDtoMapper _userDtoMapper;

    @Override
    public Response<UserResponse> findUserByDocumentNumberOrEmail(GetByUsernameOrEmail request) {
        try {
            Optional<User> userFound = _userRepositoryPort.findByEmail(request.usernameOrEmail(), request.statusCommons().status())
                    .or(() -> _userRepositoryPort.findByUsername(request.usernameOrEmail(), request.statusCommons().status()));
            if (userFound.isEmpty())
                return Response.error(UserErrorCatalog.NOT_FOUND);

            UserResponse response = _userDtoMapper.toResponse(userFound.get());
            return Response.success(response, "User found successfully");

        } catch (Exception ex) {
            return Response.error(Error.of("Exception", ex.getMessage()));
        }
    }

    @Override
    public Response<List<UserMinimalResponse>> findAllUsers(StatusCommons request) {
        try {
            List<User> usersFound = _userRepositoryPort.findAll(request.status());
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

    @Override
    public Response<List<UserMinimalResponse>> findAllUsersByFilters(FiltersCommons request) {
        try {
            List<User> usersFound = _userRepositoryPort.findAllByFilters(request);
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

    @Override
    public Response<UserResponse> findUserById(UUID id) {
        try {
            Optional<User> userFound = _userRepositoryPort.findById(id);
            if (userFound.isEmpty())
                return Response.error(UserErrorCatalog.NOT_FOUND);

            UserResponse response = _userDtoMapper.toResponse(userFound.get());

            return Response.success(response, "User found successfully");
        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<ChangeStatusResponse> changeStatus(UUID userId, Status status) {
        try {
            Optional<User> userFound = _userRepositoryPort.findById(userId);
            if (userFound.isEmpty())
                return Response.error(UserErrorCatalog.NOT_FOUND);

            if (!statusIsValid(status, userFound.get().getStatus())) {
                Error error = Error.of("Status", status.toString());
                if (status.equals(Status.ACTIVE))
                    error = UserErrorCatalog.ALREADY_ACTIVE;
                if (status.equals(Status.INACTIVE))
                    error = UserErrorCatalog.ALREADY_INACTIVE;
                return Response.error(error);
            }

            int userUpdated = _userRepositoryPort.changeUserStatus(userId, status);
            if (userUpdated == 0)
                return Response.error(UserErrorCatalog.ERROR_UPDATING);

            return Response.success(new ChangeStatusResponse(
                    userFound.get().getId(),
                    status
            ), "User ChangeStatus Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<EntityIdResponse> updateOne(UUID userId, UpdateUser request) {
        try {
            Optional<User> userFound = _userRepositoryPort.findById(userId);
            if (userFound.isEmpty())
                return Response.error(UserErrorCatalog.NOT_FOUND);

            User userUpdated = _userRepositoryPort.save(
                    _userDtoMapper.updateToDomainUser(userFound.get(), request));

            return Response.success(new EntityIdResponse(
                    userUpdated.getId()
            ), "User Updated Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<EntityIdResponse> deleteOne(UUID userId) {
        try {
            Optional<User> userFound = _userRepositoryPort.findById(userId);
            if (userFound.isEmpty())
                return Response.error(UserErrorCatalog.NOT_FOUND);

            UUID response = _userRepositoryPort.deleteOne(userFound.get().getId());
            return Response.success(new EntityIdResponse(response), "User Deleted Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    private boolean statusIsValid(Status newStatus, Status currentStatus) {
        return !newStatus.equals(currentStatus);
    }


}
