package com.sam.insuranceservice.infraestructure.persistence.entity.user;

import com.sam.insuranceservice.domain.model.enums.Role;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.infraestructure.persistence.entity.BaseEntity;
import com.sam.insuranceservice.infraestructure.persistence.entity.BasePeopleEmbeddable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@Entity
@Table(name = "users")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Embedded
    private BasePeopleEmbeddable people;
    private String phoneNumber;
    private String jobTitle;
    @Enumerated(EnumType.STRING)
    private Status status;
}
