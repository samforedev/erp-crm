package com.sam.insuranceservice.domain.port;

import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.domain.model.customer.Customer;
import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByDocumentNumber(String documentNumber);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAllByFilters(FiltersCommons request);
    List<Customer> findAll();
    List<Customer> findAllByAssignedAgentId(UUID assignedAgentId);
    int changeStatus(UUID id, Status status);
    int changeCustomerStatus(UUID id, CustomerStatus customerStatus);
    UUID delete(UUID id);
}
