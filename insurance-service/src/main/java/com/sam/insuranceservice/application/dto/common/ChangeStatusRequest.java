package com.sam.insuranceservice.application.dto.common;

import com.sam.insuranceservice.domain.model.enums.Status;

import java.util.UUID;

public record ChangeStatusRequest(
        UUID entityId,
        Status status
) {
}
