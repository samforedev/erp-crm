package com.sam.insuranceservice.infraestructure.persistence.repository;

import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
