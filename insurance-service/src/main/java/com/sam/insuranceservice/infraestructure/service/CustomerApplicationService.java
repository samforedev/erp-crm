package com.sam.insuranceservice.infraestructure.service;

import com.sam.insuranceservice.application.dto.common.ChangeStatusRequest;
import com.sam.insuranceservice.application.dto.common.ChangeStatusResponse;
import com.sam.insuranceservice.application.dto.common.EntityIdResponse;
import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.application.dto.common.response.Error;
import com.sam.insuranceservice.application.dto.common.response.Response;
import com.sam.insuranceservice.application.dto.customer.*;
import com.sam.insuranceservice.application.dto.error.CustomerErrorCatalog;
import com.sam.insuranceservice.application.mapper.CustomerDtoMapper;
import com.sam.insuranceservice.application.service.ICustomerApplicationService;
import com.sam.insuranceservice.domain.model.customer.Customer;
import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.ICustomerRepositoryPort;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CustomerApplicationService implements ICustomerApplicationService {

    private final ICustomerRepositoryPort _customerRepositoryPort;
    private final IUserRepositoryPort _userRepositoryPort;
    private final CustomerDtoMapper _customerDtoMapper;

    @Override
    public Response<EntityIdResponse> createCustomer(CustomerCreationRequest request) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findByDocumentNumber(request.documentNumber());
            if (customerFound.isPresent())
                return Response.error(CustomerErrorCatalog.ALREADY_EXIST);

            Customer newCustomer = _customerRepositoryPort.save(
                    _customerDtoMapper.toDomain(request));

            return Response.success(new EntityIdResponse(
                    newCustomer.getId()
            ), "Customer created successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<CustomerResponse> findCustomerById(UUID id) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findById(id);
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            CustomerResponse response = _customerDtoMapper.toResponse(customerFound.get());
            return Response.success(response, "Customer found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<CustomerResponse> findCustomerByEmail(String email) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findByEmail(email);
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            CustomerResponse response = _customerDtoMapper.toResponse(customerFound.get());
            return Response.success(response, "Customer found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<CustomerResponse> findCustomerByDocumentNumber(String documentNumber) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findByDocumentNumber(documentNumber);
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            CustomerResponse response = _customerDtoMapper.toResponse(customerFound.get());
            return Response.success(response, "Customer found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<List<CustomerMinimalResponse>> findAllCustomers() {
        try {
            List<Customer> customersFound = _customerRepositoryPort.findAll();
            if (customersFound.isEmpty())
                return Response.success(Collections.emptyList(), "Users not found");

            List<CustomerMinimalResponse> response = customersFound.stream()
                    .map(_customerDtoMapper::toMinimalResponse)
                    .toList();
            return Response.success(response, "Customers found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<List<CustomerMinimalResponse>> findAllCustomersByFilters(FiltersCommons filters) {
        try {
            List<Customer> customersFound = _customerRepositoryPort.findAllByFilters(filters);
            if (customersFound.isEmpty())
                return Response.success(Collections.emptyList(), "Users not found");

            List<CustomerMinimalResponse> response = customersFound.stream()
                    .map(_customerDtoMapper::toMinimalResponse)
                    .toList();
            return Response.success(response, "Customers found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<CustomerAgentResponse> findAllCustomersByAssignedAgentId(UUID assignedAgentId) {
        try {
            Optional<User> agentFound = _userRepositoryPort.findById(assignedAgentId);
            if (agentFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_AGENT_FOUND);

            List<Customer> customersFound = _customerRepositoryPort.findAllByAssignedAgentId(assignedAgentId);
            if (customersFound.isEmpty())
                return Response.success(new CustomerAgentResponse(
                        agentFound.get().getId(),
                        agentFound.get().getPeople().firstName(),
                        agentFound.get().getEmail(),
                        agentFound.get().getJobTitle(),
                        Collections.emptyList()
                ), "Users not found");


            CustomerAgentResponse response = _customerDtoMapper.toAgentResponse(agentFound.get(), customersFound);
            return Response.success(response, "Customers found successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<ChangeStatusResponse> changeStatus(ChangeStatusRequest request) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findById(request.entityId());
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            boolean statusIsValid = !request.status().equals(customerFound.get().getStatus());
            if (!statusIsValid)
                return Response.error(CustomerErrorCatalog.ALREADY_STATUS);

            int customerUpdated = _customerRepositoryPort.changeStatus(request.entityId(), request.status());
            if (customerUpdated == 0)
                return Response.error(CustomerErrorCatalog.ERROR_UPDATING);

            return Response.success(new ChangeStatusResponse(
                    request.entityId(),
                    request.status()
            ), "Customer ChangeStatus Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<ChangeCustomerStatusResponse> changeCustomerStatus(ChangeCustomerStatusRequest request) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findById(request.customerId());
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            boolean customerStatusIsValid = !request.customerStatus().equals(customerFound.get().getCustomerStatus());
            if (!customerStatusIsValid)
                return Response.error(CustomerErrorCatalog.ALREADY_STATUS);

            int customerUpdated = _customerRepositoryPort.changeCustomerStatus(request.customerId(), request.customerStatus());
            if (customerUpdated == 0)
                return Response.error(CustomerErrorCatalog.ERROR_UPDATING);

            return Response.success(new ChangeCustomerStatusResponse(
                    request.customerId(),
                    request.customerStatus()
            ), "Customer ChangeStatus Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<EntityIdResponse> updateOne(UUID customerId, CustomerUpdateRequest request) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findById(customerId);
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            Customer customerUpdated = _customerRepositoryPort.save(
                    _customerDtoMapper.updateToDomainCustomer(customerFound.get(), request));

            return Response.success(new EntityIdResponse(
                    customerUpdated.getId()
            ), "Customer Updated Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<EntityIdResponse> deleteOne(UUID customerId) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findById(customerId);
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            UUID response = _customerRepositoryPort.delete(customerId);
            return Response.success(new EntityIdResponse(response), "Customer Deleted Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }

    @Override
    public Response<CustomerResponse> assignAgent(UUID customerId, AssignAgentRequest request) {
        try {
            Optional<Customer> customerFound = _customerRepositoryPort.findById(customerId);
            if (customerFound.isEmpty())
                return Response.error(CustomerErrorCatalog.NOT_FOUND);

            Customer customerUpdated = _customerRepositoryPort.save(
                    _customerDtoMapper.assignToDomainCustomer(customerFound.get(), request));

            CustomerResponse response = _customerDtoMapper.toResponse(customerUpdated);
            return Response.success(response, "Customer Agent Assigned Successfully");

        } catch (Exception exception) {
            return Response.error(Error.of("Exception", exception.getMessage()));
        }
    }
}
