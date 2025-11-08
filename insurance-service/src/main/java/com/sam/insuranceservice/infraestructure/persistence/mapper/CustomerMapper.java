package com.sam.insuranceservice.infraestructure.persistence.mapper;

import com.sam.insuranceservice.domain.model.customer.Customer;
import com.sam.insuranceservice.infraestructure.persistence.entity.customer.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final CommonsMapper _commonsMapper;

    public CustomerEntity toJpaEntity(Customer domainCustomer) {
        if (domainCustomer == null) return null;

        return CustomerEntity.builder()
                .id(domainCustomer.getId())
                .createdAt(domainCustomer.getCreatedAt())
                .updatedAt(domainCustomer.getUpdatedAt())
                .deleted(domainCustomer.isDeleted())
                .people(_commonsMapper.toEmbeddable(domainCustomer.getPeople()))
                .email(domainCustomer.getEmail())
                .phoneNumber(domainCustomer.getPhoneNumber())
                .assignedAgentId(domainCustomer.getAssignedAgentId())
                .customerStatus(domainCustomer.getCustomerStatus())
                .build();
    }

    public Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setCreatedAt(entity.getCreatedAt());
        customer.setUpdatedAt(entity.getUpdatedAt());
        customer.setDeleted(entity.isDeleted());
        customer.setPeople(_commonsMapper.toDomainRecord(entity.getPeople()));
        customer.setEmail(entity.getEmail());
        customer.setPhoneNumber(entity.getPhoneNumber());
        customer.setAssignedAgentId(entity.getAssignedAgentId());
        customer.setCustomerStatus(entity.getCustomerStatus());

        return customer;
    }
}
