package com.sam.insuranceservice.infraestructure.controller;

import com.sam.insuranceservice.application.dto.common.ChangeStatusRequest;
import com.sam.insuranceservice.application.dto.common.ChangeStatusResponse;
import com.sam.insuranceservice.application.dto.common.EntityIdResponse;
import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.application.dto.common.response.Response;
import com.sam.insuranceservice.application.dto.customer.*;
import com.sam.insuranceservice.application.service.ICustomerApplicationService;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.infraestructure.util.ResponseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerApplicationService _customerApplicationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> createCustomer(@Validated @RequestBody CustomerCreationRequest request){
        Response<EntityIdResponse> serviceResult = _customerApplicationService.createCustomer(request);
        Object response = ResponseValidator.validator(serviceResult);
        return ResponseEntity.status(serviceResult.success() ? 201 : 400).body(response);
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_READ')")
    public ResponseEntity<Object> getCustomerById(@PathVariable UUID customerId) {
        Response<CustomerResponse> serviceResult = _customerApplicationService.findCustomerById(customerId);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @GetMapping("/getByEmail/{email}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_READ')")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        Response<CustomerResponse> serviceResult = _customerApplicationService.findCustomerByEmail(email);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @GetMapping("/getByDocumentNumber/{documentNumber}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_READ')")
    public ResponseEntity<Object> getCustomerByDocumentNumber(@PathVariable String documentNumber) {
        Response<CustomerResponse> serviceResult = _customerApplicationService.findCustomerByDocumentNumber(documentNumber);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('CUSTOMER_READ')")
    public ResponseEntity<Object> getAllCustomer() {
        Response<List<CustomerMinimalResponse>> serviceResult = _customerApplicationService.findAllCustomers();
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/getByFilters")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_READ')")
    public ResponseEntity<Object> getAllCustomerByFilters(@Validated @RequestBody FiltersCommons filters) {
        Response<List<CustomerMinimalResponse>> serviceResult =
                _customerApplicationService.findAllCustomersByFilters(filters);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @GetMapping("/getByAgentId/{agentId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_READ')")
    public ResponseEntity<Object> getAllCustomerByAgentId(@Validated @PathVariable UUID agentId) {
        Response<CustomerAgentResponse> serviceResult =
                _customerApplicationService.findAllCustomersByAssignedAgentId(agentId);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PutMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> updateCustomer(
            @PathVariable UUID customerId,
            @Validated @RequestBody CustomerUpdateRequest request){
        Response<EntityIdResponse> serviceResult = _customerApplicationService.updateOne(customerId, request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @DeleteMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> deleteCustomer(@PathVariable UUID customerId) {
        Response<EntityIdResponse> serviceResult = _customerApplicationService.deleteOne(customerId);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/{customerId}/activate")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> activateCustomer(@PathVariable UUID customerId) {
        Response<ChangeStatusResponse> serviceResult = _customerApplicationService.changeStatus(new ChangeStatusRequest(
                customerId, Status.ACTIVE));
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/{customerId}/deactivate")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> deactivateCustomer(@PathVariable UUID customerId) {
        Response<ChangeStatusResponse> serviceResult = _customerApplicationService.changeStatus(new ChangeStatusRequest(
                customerId, Status.INACTIVE));
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/changeCustomerStatus")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> changeCustomerStatus(@Validated @RequestBody ChangeCustomerStatusRequest request) {
        Response<ChangeCustomerStatusResponse> serviceResult = _customerApplicationService.changeCustomerStatus(request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/{customerId}/assignAgent")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<Object> customerAssignAgent(
            @PathVariable UUID customerId,
            @Validated @RequestBody AssignAgentRequest request) {
        Response<CustomerResponse> serviceResult = _customerApplicationService.assignAgent(customerId, request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }
}
