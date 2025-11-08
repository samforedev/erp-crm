package com.sam.insuranceservice.application.mapper;

import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;
import com.sam.insuranceservice.domain.model.user.Role;
import com.sam.insuranceservice.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserResponse toResponse(User domainUser) {
        return new UserResponse(
                domainUser.getId(),
                domainUser.getPeople(),
                domainUser.getPhoneNumber(),
                domainUser.getJobTitle(),
                domainUser.getUsername(),
                domainUser.getEmail(),
                domainUser.getRoles().stream()
                        .map(Role::getName)
                        .toList(),
                domainUser.getStatus(),
                domainUser.getCreatedAt(),
                domainUser.isDeleted()
        );
    }

    public UserMinimalResponse toMinimalResponse(User domainUser) {
        return new UserMinimalResponse(
                domainUser.getId(),
                domainUser.getPeople().firstName(),
                domainUser.getEmail(),
                domainUser.getRoles().stream()
                        .map(Role::getName)
                        .toList(),
                domainUser.getJobTitle()
        );
    }

}
