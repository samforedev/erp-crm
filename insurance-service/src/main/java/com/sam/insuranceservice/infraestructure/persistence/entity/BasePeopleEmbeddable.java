package com.sam.insuranceservice.infraestructure.persistence.entity;

import com.sam.insuranceservice.domain.model.enums.DocumentType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class BasePeopleEmbeddable {
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;
    private LocalDate birthDate;
}
