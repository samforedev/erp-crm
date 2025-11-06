package com.sam.insuranceservice.application.service;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.customer.CustomerCreationRequest;
import com.sam.insuranceservice.application.dto.customer.CustomerMinimalResponse;
import com.sam.insuranceservice.application.dto.customer.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface ICustomerApplicationService {
    Response<UUID> createCustomer(CustomerCreationRequest request);
    Response<CustomerResponse> findCustomerById(UUID id);
    Response<List<CustomerMinimalResponse>> findAllCustomers();
    Response<UUID> updateUser(UUID id);
    Response<UUID> deleteUser(UUID id);
}
