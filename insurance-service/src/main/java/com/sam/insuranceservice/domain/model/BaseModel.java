package com.sam.insuranceservice.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseModel {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean isDeleted;
}
