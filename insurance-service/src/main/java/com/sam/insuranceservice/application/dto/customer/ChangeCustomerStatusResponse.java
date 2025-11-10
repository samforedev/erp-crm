package com.sam.insuranceservice.application.dto.customer;

import com.sam.insuranceservice.domain.model.enums.CustomerStatus;

import java.util.UUID;

public record ChangeCustomerStatusResponse(
        UUID customerId,
        CustomerStatus currentCustomerStatus
) {
}
