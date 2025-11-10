package com.sam.insuranceservice.infraestructure.persistence.entity.customer;

import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.infraestructure.persistence.entity.BaseEntity;
import com.sam.insuranceservice.infraestructure.persistence.entity.BasePeopleEmbeddable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerEntity extends BaseEntity {
    @Embedded
    private BasePeopleEmbeddable people;

    @Column(unique = true, nullable = false)

    private String email;
    private String phoneNumber;
    private UUID assignedAgentId;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;
    @Enumerated(EnumType.STRING)
    private Status status;


}
