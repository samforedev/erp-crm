package com.sam.insuranceservice.application.dto.customer;

import java.util.UUID;

public record AssignAgentRequest(
        UUID agentId
) {
}
