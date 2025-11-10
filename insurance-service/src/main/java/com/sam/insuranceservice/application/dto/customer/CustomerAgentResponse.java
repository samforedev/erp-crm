package com.sam.insuranceservice.application.dto.customer;

import java.util.List;
import java.util.UUID;

public record CustomerAgentResponse(
        UUID agentId,
        String firstName,
        String email,
        String jobTitle,
        List<CustomerMinimalResponse> customers
) {
}
