package com.sam.insuranceservice.domain.model;

import com.sam.insuranceservice.domain.model.enums.DocumentType;

import java.time.LocalDate;

public record BasePeople(
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        LocalDate birthDate
){ }
