package com.sam.insuranceservice.application.mapper;

import com.sam.insuranceservice.application.dto.customer.*;
import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.customer.Customer;
import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerDtoMapper {

    public Customer toDomain(CustomerCreationRequest request) {
        BasePeople people = new BasePeople(
                request.firstName(),
                request.lastName(),
                request.documentType(),
                request.documentNumber(),
                request.birthDate()
        );

        return Customer.builder()
                .people(people)
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .assignedAgentId(null)
                .customerStatus(CustomerStatus.NEW)
                .status(Status.ACTIVE)
                .build();
    }

    public CustomerResponse toResponse(Customer domainCustomer) {
        return new CustomerResponse(
                domainCustomer.getId(),
                domainCustomer.getPeople(),
                domainCustomer.getEmail(),
                domainCustomer.getPhoneNumber(),
                domainCustomer.getAssignedAgentId(),
                domainCustomer.getCustomerStatus(),
                domainCustomer.getStatus(),
                domainCustomer.getCreatedAt(),
                domainCustomer.isDeleted()
        );
    }

    public CustomerMinimalResponse toMinimalResponse(Customer domainCustomer) {
        return new CustomerMinimalResponse(
                domainCustomer.getId(),
                domainCustomer.getEmail(),
                domainCustomer.getCustomerStatus()
        );
    }

    public CustomerAgentResponse toAgentResponse(User agent, List<Customer> domainCustomer) {
        List<CustomerMinimalResponse> customers = domainCustomer.stream()
                .map(this::toMinimalResponse)
                .toList();
        return new CustomerAgentResponse(
                agent.getId(),
                agent.getPeople().firstName(),
                agent.getEmail(),
                agent.getJobTitle(),
                customers
        );
    }

    public Customer updateToDomainCustomer(Customer existingCustomer, CustomerUpdateRequest request) {
        if (request == null) return existingCustomer;

        Customer.CustomerBuilder<?, ? extends Customer.CustomerBuilder<?, ?>> customerBuilder = existingCustomer.toBuilder();
        if (request.email() != null) customerBuilder.email(request.email());
        if (request.phoneNumber() != null) customerBuilder.phoneNumber(request.phoneNumber());

        BasePeople updatedPeople = getBasePeople(existingCustomer, request);
        customerBuilder.people(updatedPeople);
        customerBuilder.updatedAt(Instant.now());
        return customerBuilder.build();
    }

    public Customer assignToDomainCustomer(Customer existingCustomer, AssignAgentRequest request) {
        if (request == null) return existingCustomer;
        Customer.CustomerBuilder<?, ? extends Customer.CustomerBuilder<?, ?>> customerBuilder = existingCustomer.toBuilder();

        customerBuilder.assignedAgentId(request.agentId());
        return customerBuilder.build();
    }

    private static BasePeople getBasePeople(Customer existingCustomer, CustomerUpdateRequest request) {
        BasePeople existingPeople = existingCustomer.getPeople();
        return new BasePeople(
                request.firstName() != null ? request.firstName() : existingPeople.firstName(),
                request.lastName() != null ? request.lastName() : existingPeople.lastName(),
                request.documentType() != null ? request.documentType() : existingPeople.documentType(),
                request.documentNumber() != null ? request.documentNumber() : existingPeople.documentNumber(),
                request.birthDate() != null ? request.birthDate() : existingPeople.birthDate()
        );
    }
}
