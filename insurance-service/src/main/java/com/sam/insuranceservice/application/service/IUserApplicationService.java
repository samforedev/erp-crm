package com.sam.insuranceservice.application.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.user.GetByUsernameOrEmail;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;

import java.util.List;


public interface IUserApplicationService {
    Response<UserResponse> findUserByDocumentNumberOrEmail(GetByUsernameOrEmail request);
    Response<List<UserMinimalResponse>> findAllUsers();
}
