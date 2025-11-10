package com.sam.insuranceservice.application.dto.customer;

import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.DocumentType;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.time.LocalDate;

public record CustomerCreationRequest (
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate,
        String email,
        String phoneNumber
) { }
