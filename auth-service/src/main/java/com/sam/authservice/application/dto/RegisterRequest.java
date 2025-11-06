package com.sam.authservice.application.dto;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String initialRoleName
) {
}
