package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.user.UserCreationRequest;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;
import com.sam.insuranceservice.application.service.IUserApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements IUserApplicationService {

    @Override
    public Response<UUID> createUser(UserCreationRequest request) {
        return null;
    }

    @Override
    public Response<UserResponse> findUserById(UUID id) {
        return null;
    }

    @Override
    public Response<List<UserMinimalResponse>> findAllUsers() {
        return null;
    }

    @Override
    public Response<UUID> updateUser(UUID id) {
        return null;
    }

    @Override
    public Response<UUID> deleteUser(UUID id) {
        return null;
    }
}
