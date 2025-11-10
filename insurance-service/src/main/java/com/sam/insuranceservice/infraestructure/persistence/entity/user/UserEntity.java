package com.sam.insuranceservice.infraestructure.persistence.entity.user;

import com.sam.insuranceservice.infraestructure.persistence.entity.BaseEntity;
import com.sam.insuranceservice.infraestructure.persistence.entity.BasePeopleEmbeddable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


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

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<RoleEntity> roles = new HashSet<>();
}
