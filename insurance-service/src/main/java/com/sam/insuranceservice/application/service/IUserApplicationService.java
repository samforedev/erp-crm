package com.sam.insuranceservice.application.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.user.UserCreationRequest;
import java.util.UUID;

public interface IUserApplicationService {
    Response<UUID> createUser(UserCreationRequest request);
}
