package com.sam.insuranceservice.infraestructure.controller;

import com.sam.insuranceservice.application.dto.common.ChangeStatusResponse;
import com.sam.insuranceservice.application.dto.common.EntityIdResponse;
import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.application.dto.common.StatusCommons;
import com.sam.insuranceservice.application.dto.common.response.Response;
import com.sam.insuranceservice.application.dto.user.*;
import com.sam.insuranceservice.application.service.IUserApplicationService;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserApplicationService _userApplicationService;

    @PostMapping("/getOneByDocumentOrEmail")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Object> getUserByDocumentNumberOrEmail(@Validated @RequestBody GetByUsernameOrEmail request) {
        Response<UserResponse> serviceResult = _userApplicationService.findUserByDocumentNumberOrEmail(request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Object> getAllUsers(@Validated @RequestBody StatusCommons request) {
        Response<List<UserMinimalResponse>> serviceResult = _userApplicationService.findAllUsers(request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/getByFilters")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Object> getByFilters(@Validated @RequestBody FiltersCommons request) {
        Response<List<UserMinimalResponse>> serviceResult = _userApplicationService.findAllUsersByFilters(request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Object> getUserById(@PathVariable UUID userId) {
        Response<UserResponse> serviceResult = _userApplicationService.findUserById(userId);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/{userId}/activate")
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity<Object> activateUser(@PathVariable UUID userId) {
        Response<ChangeStatusResponse> serviceResult = _userApplicationService.changeStatus(userId, Status.ACTIVE);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PostMapping("/{userId}/deactivate")
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity<Object> deactivateUser(@PathVariable UUID userId) {
        Response<ChangeStatusResponse> serviceResult = _userApplicationService.changeStatus(userId, Status.INACTIVE);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity<Object> updateUser(@Validated @PathVariable UUID userId, @RequestBody UpdateUser request) {
        Response<EntityIdResponse> serviceResult = _userApplicationService.updateOne(userId, request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @DeleteMapping("/{userId}/delete")
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {
        Response<EntityIdResponse> serviceResult = _userApplicationService.deleteOne(userId);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

}
