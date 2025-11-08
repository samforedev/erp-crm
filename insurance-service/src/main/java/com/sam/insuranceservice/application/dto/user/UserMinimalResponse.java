package com.sam.insuranceservice.application.dto.user;


import java.util.List;
import java.util.UUID;

public record UserMinimalResponse(
        UUID id,
        String firstName,
        String email,
        List<String> roles,
        String jobTitle
) {
}
