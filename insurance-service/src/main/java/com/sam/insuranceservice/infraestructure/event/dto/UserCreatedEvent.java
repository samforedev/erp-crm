package com.sam.insuranceservice.infraestructure.event.dto;

import com.sam.insuranceservice.domain.model.enums.DocumentType;

import java.time.LocalDate;
import java.util.UUID;

public record UserCreatedEvent(
        UUID autUserId,
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate,
        String phoneNumber,
        String jobTitle
) { }
