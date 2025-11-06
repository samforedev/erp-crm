package com.sam.insuranceservice.application.mapper;

import com.sam.insuranceservice.application.dto.customer.CustomerCreationRequest;
import com.sam.insuranceservice.application.dto.customer.CustomerMinimalResponse;
import com.sam.insuranceservice.application.dto.customer.CustomerResponse;
import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
                .customerStatus(request.customerStatus())
                .status(request.status())
                .build();
    }

    public CustomerResponse toResponse(Customer domainCustomer) {
        return new CustomerResponse(
                domainCustomer.getId(),
                domainCustomer.getEmail(),
                domainCustomer.getPhoneNumber(),
                domainCustomer.getAssignedAgentId(),
                domainCustomer.getCustomerStatus(),
                domainCustomer.getStatus(),
                domainCustomer.getCreatedAt(),
                domainCustomer.getPeople(),
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

}
