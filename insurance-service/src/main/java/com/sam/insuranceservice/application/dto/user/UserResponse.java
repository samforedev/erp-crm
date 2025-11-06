package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.Role;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.time.Instant;
import java.util.UUID;

public record UserResponse (
        UUID id,
        String username,
        String email,
        Role role,
        String jobTitle,
        Status status,
        Instant lastLogin,
        Instant createdAt,
        BasePeople people,
        boolean isDeleted
) { }
