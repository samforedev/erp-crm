package com.sam.insuranceservice.infraestructure.controller;

import com.sam.insuranceservice.application.dto.common.Response;
import com.sam.insuranceservice.application.dto.user.GetByUsernameOrEmail;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;
import com.sam.insuranceservice.application.service.IUserApplicationService;
import com.sam.insuranceservice.infraestructure.util.ResponseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserApplicationService _userApplicationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Object> getUserByDocumentNumberOrEmail(@Validated @RequestBody GetByUsernameOrEmail request) {
        Response<UserResponse> serviceResult = _userApplicationService.findUserByDocumentNumberOrEmail(request);
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Object> getAllUsers() {
        Response<List<UserMinimalResponse>> serviceResult = _userApplicationService.findAllUsers();
        return ResponseEntity.ok(ResponseValidator.validator(serviceResult));
    }
}
