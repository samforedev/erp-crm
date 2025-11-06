package com.sam.authservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
