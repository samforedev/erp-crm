package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.domain.model.enums.DocumentType;

import java.time.LocalDate;

public record UpdateUser(
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate,
        String phoneNumber,
        String jobTitle,
        String username
) { }
