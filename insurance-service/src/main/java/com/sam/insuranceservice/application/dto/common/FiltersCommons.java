package com.sam.insuranceservice.application.dto.common;

import com.sam.insuranceservice.domain.model.enums.DocumentType;
import com.sam.insuranceservice.domain.model.enums.Status;

public record FiltersCommons(
        DocumentType documentType,
        String documentNumber,
        String firstName,
        String username,
        String email,
        Status status
) {
}
