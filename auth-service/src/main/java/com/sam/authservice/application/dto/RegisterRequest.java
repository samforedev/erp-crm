package com.sam.authservice.application.dto;

import com.sam.authservice.domain.enums.DocumentType;

import java.time.LocalDate;

public record RegisterRequest(
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate,
        String phoneNumber,
        String jobTitle,
        String username,
        String email,
        String password,
        String initialRoleName
) { }
