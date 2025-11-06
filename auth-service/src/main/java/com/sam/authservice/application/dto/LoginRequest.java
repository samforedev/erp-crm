package com.sam.authservice.application.dto;

public record LoginRequest(
        String usernameOrEmail,
        String password
) {
}
