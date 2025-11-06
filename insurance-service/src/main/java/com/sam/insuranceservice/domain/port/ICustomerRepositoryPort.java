package com.sam.insuranceservice.domain.port;

import com.sam.insuranceservice.domain.model.customer.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepositoryPort {
    // Crud Base
    Customer save(Customer customer);
    Optional<Customer> findById(UUID id);
    List<Customer> findAll();
    UUID update(UUID id, Customer customer);
    UUID delete(UUID id);
}
