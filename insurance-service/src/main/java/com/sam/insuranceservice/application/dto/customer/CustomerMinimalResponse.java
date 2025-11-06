package com.sam.insuranceservice.application.dto.customer;

import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import java.util.UUID;

public record CustomerMinimalResponse(
        UUID id,
        String email,
        CustomerStatus customerStatus
) {
}
