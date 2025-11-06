package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.domain.model.enums.Role;

import java.util.UUID;

public record UserMinimalResponse(
        UUID id,
        String username,
        String email,
        Role role,
        String jobTitle
) {
}
