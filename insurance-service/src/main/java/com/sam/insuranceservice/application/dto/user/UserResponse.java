package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.time.Instant;
import java.util.UUID;

public record UserResponse (
        UUID id,
        BasePeople people,
        String email,
        String phoneNumber,
        String jobTitle,
        String role,
        Status status,
        Instant createdAt,
        boolean deleted
) { }
