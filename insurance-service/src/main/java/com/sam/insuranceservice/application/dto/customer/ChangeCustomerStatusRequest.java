package com.sam.insuranceservice.application.dto.customer;

import com.sam.insuranceservice.domain.model.enums.CustomerStatus;

import java.util.UUID;

public record ChangeCustomerStatusRequest(
        UUID customerId,
        CustomerStatus customerStatus
) {
}
