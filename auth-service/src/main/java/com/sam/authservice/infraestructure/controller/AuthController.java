package com.sam.authservice.infraestructure.controller;

import com.sam.authservice.application.dto.LoginRequest;
import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.application.dto.TokenResponse;
import com.sam.authservice.application.service.IAuthApplicationService;
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
    public ResponseEntity<UUID> register(@RequestBody RegisterRequest request) {
        UUID registeredUser = _authApplicationService.registerUser(request);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = _authApplicationService.loginUser(request);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

}
