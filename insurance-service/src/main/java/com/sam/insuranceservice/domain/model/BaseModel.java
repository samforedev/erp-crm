package com.sam.insuranceservice.domain.model;

import com.sam.insuranceservice.domain.model.enums.Status;
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
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean deleted;
}
