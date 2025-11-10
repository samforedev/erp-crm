package com.sam.insuranceservice.infraestructure.persistence.adapter;

import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.domain.model.customer.Customer;
import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.domain.port.ICustomerRepositoryPort;
import com.sam.insuranceservice.infraestructure.persistence.entity.customer.CustomerEntity;
import com.sam.insuranceservice.infraestructure.persistence.mapper.CustomerMapper;
import com.sam.insuranceservice.infraestructure.persistence.repository.ICustomerJpaRepository;
import com.sam.insuranceservice.infraestructure.util.BuildsSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomerAdapter implements ICustomerRepositoryPort {

    private final ICustomerJpaRepository _customerJpaRepository;
    private final CustomerMapper _customerMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = _customerMapper.toEntity(customer);
        CustomerEntity savedEntity = _customerJpaRepository.save(entity);
        return _customerMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return _customerJpaRepository.findByIdAndDeletedIsFalse(id)
                .map(_customerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        return _customerJpaRepository.findByPeopleDocumentNumberAndDeletedIsFalse(documentNumber)
                .map(_customerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return _customerJpaRepository.findByEmailAndDeletedIsFalse(email)
                .map(_customerMapper::toDomain);
    }

    @Override
    public List<Customer> findAllByFilters(FiltersCommons request) {
        Specification<CustomerEntity> spec = BuildsSpecification.buildFiltersCommons(request);
        List<CustomerEntity> result = _customerJpaRepository.findAll(spec);
        return result.stream()
                .map(_customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAll() {
        return _customerJpaRepository.findAllByDeletedIsFalse()
                .stream()
                .map(_customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllByAssignedAgentId(UUID assignedAgentId) {
        return _customerJpaRepository.findAllByAssignedAgentIdAndDeletedIsFalse(assignedAgentId)
                .stream()
                .map(_customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public int changeStatus(UUID id, Status status) {
        return _customerJpaRepository.updateCustomerStatus(id, status);
    }

    @Override
    public int changeCustomerStatus(UUID id, CustomerStatus customerStatus) {
        return _customerJpaRepository.updateCustomerCustomerStatus(id, customerStatus);
    }

    @Override
    public UUID delete(UUID id) {
        CustomerEntity existingEntity = _customerJpaRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));

        existingEntity.setDeleted(true);
        existingEntity.setStatus(Status.DELETED);
        existingEntity.setUpdatedAt(Instant.now());
        existingEntity = _customerJpaRepository.save(existingEntity);

        return existingEntity.getId();
    }
}
