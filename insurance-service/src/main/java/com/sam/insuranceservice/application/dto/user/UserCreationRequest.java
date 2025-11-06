package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.domain.model.enums.DocumentType;
import com.sam.insuranceservice.domain.model.enums.Role;

import java.time.LocalDate;

public record UserCreationRequest(
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate,
        String username,
        String email,
        String password,
        Role role,
        String jobTitle
) { }
