package com.sam.insuranceservice.application.dto.customer;

import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String email,
        String phoneNumber,
        UUID assignedAgentId,
        CustomerStatus customerStatus,
        Status status,
        Instant createdAt,
        BasePeople people,
        boolean isDeleted
) {
}
