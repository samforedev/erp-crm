package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.customer.CustomerCreationRequest;
import com.sam.insuranceservice.application.dto.customer.CustomerMinimalResponse;
import com.sam.insuranceservice.application.dto.customer.CustomerResponse;
import com.sam.insuranceservice.application.service.ICustomerApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService implements ICustomerApplicationService {
    @Override
    public Response<UUID> createCustomer(CustomerCreationRequest request) {
        return null;
    }

    @Override
    public Response<CustomerResponse> findCustomerById(UUID id) {
        return null;
    }

    @Override
    public Response<List<CustomerMinimalResponse>> findAllCustomers() {
        return null;
    }

    @Override
    public Response<UUID> updateUser(UUID id) {
        return null;
    }

    @Override
    public Response<UUID> deleteUser(UUID id) {
        return null;
    }
}
