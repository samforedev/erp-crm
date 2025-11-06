package com.sam.authservice.application.service;

import com.sam.authservice.application.dto.LoginRequest;
import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.application.dto.TokenResponse;
import com.sam.authservice.domain.model.User;

import java.util.UUID;

public interface IAuthApplicationService {
    UUID registerUser(RegisterRequest request);
    TokenResponse loginUser(LoginRequest request);
    User getUserDetailsForToken(UUID userId);
}
