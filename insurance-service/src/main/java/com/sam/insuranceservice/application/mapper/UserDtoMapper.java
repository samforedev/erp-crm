package com.sam.insuranceservice.application.mapper;

import com.sam.insuranceservice.application.dto.user.UpdateUser;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;
import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.user.Role;
import com.sam.insuranceservice.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

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
                domainUser.getJobTitle(),
                domainUser.getStatus()
        );
    }

    public User updateToDomainUser(User existingUser, UpdateUser request) {
        if (request == null) return existingUser;
        User.UserBuilder<?, ? extends User.UserBuilder<?, ?>> userBuilder = existingUser.toBuilder();
        userBuilder.roles(existingUser.getRoles());
        if (request.phoneNumber() != null)
            userBuilder.phoneNumber(request.phoneNumber());
        if (request.jobTitle() != null)
            userBuilder.jobTitle(request.jobTitle());
        if (request.username() != null)
            userBuilder.username(request.username());

        BasePeople updatedPeople = getBasePeople(existingUser, request);
        userBuilder.people(updatedPeople);
        userBuilder.updatedAt(Instant.now());
        return userBuilder.build();
    }

    private static BasePeople getBasePeople(User existingUser, UpdateUser request) {
        BasePeople existingPeople = existingUser.getPeople();
        return new BasePeople(
                request.firstName() != null ? request.firstName() : existingPeople.firstName(),
                request.lastName() != null ? request.lastName() : existingPeople.lastName(),
                request.documentType() != null ? request.documentType() : existingPeople.documentType(),
                request.documentNumber() != null ? request.documentNumber() : existingPeople.documentNumber(),
                request.birthDate() != null ? request.birthDate() : existingPeople.birthDate()
        );
    }
}
