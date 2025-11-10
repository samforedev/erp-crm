package com.sam.insuranceservice.application.dto.customer;

import com.sam.insuranceservice.domain.model.enums.DocumentType;

import java.time.LocalDate;

public record CustomerUpdateRequest(
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate,
        String email,
        String phoneNumber
) {
}
