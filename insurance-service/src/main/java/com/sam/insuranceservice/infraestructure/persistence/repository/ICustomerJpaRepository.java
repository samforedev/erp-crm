package com.sam.insuranceservice.infraestructure.persistence.repository;

import com.sam.insuranceservice.infraestructure.persistence.entity.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
