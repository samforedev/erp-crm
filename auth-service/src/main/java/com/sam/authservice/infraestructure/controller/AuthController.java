package com.sam.authservice.infraestructure.controller;

import com.sam.authservice.application.dto.LoginRequest;
import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.application.dto.TokenResponse;
import com.sam.authservice.application.dto.common.Response;
import com.sam.authservice.application.service.IAuthApplicationService;
import com.sam.authservice.infraestructure.util.ResponseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthApplicationService _authApplicationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        Response<UUID> serviceResult = _authApplicationService.registerUser(request);
        Object response = ResponseValidator.validator(serviceResult);
        return ResponseEntity.status(serviceResult.success() ? 201 : 400).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        Response<TokenResponse> serviceResult = _authApplicationService.loginUser(request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

}
