package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.user.UserCreationRequest;
import com.sam.insuranceservice.application.service.IUserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements IUserApplicationService {

    @Override
    public Response<UUID> createUser(UserCreationRequest request) {
        return null;
    }
}
