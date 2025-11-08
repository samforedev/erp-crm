package com.sam.insuranceservice.application.service;

import com.sam.insuranceservice.application.dto.common.ChangeStatusResponse;
import com.sam.insuranceservice.application.dto.common.EntityIdResponse;
import com.sam.insuranceservice.application.dto.common.StatusCommons;
import com.sam.insuranceservice.application.dto.common.response.Response;
import com.sam.insuranceservice.application.dto.user.*;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.util.List;
import java.util.UUID;


public interface IUserApplicationService {
    Response<UserResponse> findUserByDocumentNumberOrEmail(GetByUsernameOrEmail request);
    Response<List<UserMinimalResponse>> findAllUsers(StatusCommons request);
    Response<List<UserMinimalResponse>> findAllUsersByFilters(GetUserByFilters request);
    Response<UserResponse> findUserById(UUID id);
    Response<ChangeStatusResponse> changeStatus(UUID userId, Status status);
    Response<EntityIdResponse> updateOne(UUID userId, UpdateUser request);
    Response<EntityIdResponse> deleteOne(UUID userId);
}
