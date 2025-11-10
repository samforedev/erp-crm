package com.sam.insuranceservice.application.service;

import com.sam.insuranceservice.application.dto.common.ChangeStatusRequest;
import com.sam.insuranceservice.application.dto.common.ChangeStatusResponse;
import com.sam.insuranceservice.application.dto.common.EntityIdResponse;
import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.application.dto.common.response.Response;
import com.sam.insuranceservice.application.dto.customer.*;

import java.util.List;
import java.util.UUID;

public interface ICustomerApplicationService {
    Response<EntityIdResponse> createCustomer(CustomerCreationRequest request);

    Response<CustomerResponse> findCustomerById(UUID id);
    Response<CustomerResponse> findCustomerByEmail(String email);
    Response<CustomerResponse> findCustomerByDocumentNumber(String documentNumber);

    Response<List<CustomerMinimalResponse>> findAllCustomers();
    Response<List<CustomerMinimalResponse>> findAllCustomersByFilters(FiltersCommons filters);
    Response<CustomerAgentResponse> findAllCustomersByAssignedAgentId(UUID assignedAgentId);

    Response<ChangeStatusResponse> changeStatus(ChangeStatusRequest request);
    Response<ChangeCustomerStatusResponse> changeCustomerStatus(ChangeCustomerStatusRequest request);
    Response<EntityIdResponse> updateOne(UUID customerId, CustomerUpdateRequest request);
    Response<EntityIdResponse> deleteOne(UUID customerId);

    Response<CustomerResponse> assignAgent(UUID customerId, AssignAgentRequest request);
}
