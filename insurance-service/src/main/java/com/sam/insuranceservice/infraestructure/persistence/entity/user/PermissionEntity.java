package com.sam.insuranceservice.infraestructure.persistence.entity.user;

import com.sam.insuranceservice.infraestructure.persistence.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "auth_permissions")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PermissionEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
}
