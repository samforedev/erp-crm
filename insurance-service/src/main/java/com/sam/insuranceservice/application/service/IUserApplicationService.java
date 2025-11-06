package com.sam.insuranceservice.application.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.user.UserCreationRequest;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IUserApplicationService {
    Response<UUID> createUser(UserCreationRequest request);
    Response<UserResponse> findUserById(UUID id);
    Response<List<UserMinimalResponse>> findAllUsers();
    Response<UUID> updateUser(UUID id);
    Response<UUID> deleteUser(UUID id);
}
