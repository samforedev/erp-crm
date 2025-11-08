package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserResponse (
        UUID id,
        BasePeople people,
        String phoneNumber,
        String jobTitle,
        String username,
        String email,
        List<String> roles,
        Status status,
        Instant createdAt,
        boolean deleted
) { }
