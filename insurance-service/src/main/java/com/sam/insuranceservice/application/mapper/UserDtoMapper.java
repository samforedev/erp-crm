package com.sam.insuranceservice.application.mapper;

import com.sam.insuranceservice.application.dto.user.UserCreationRequest;
import com.sam.insuranceservice.application.dto.user.UserMinimalResponse;
import com.sam.insuranceservice.application.dto.user.UserResponse;
import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public User toDomain(UserCreationRequest request) {
        BasePeople people = new BasePeople(
                request.firstName(),
                request.lastName(),
                request.documentType(),
                request.documentNumber(),
                request.birthDate()
        );

        return User.builder()
                .people(people)
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .role(request.role())
                .jobTitle(request.jobTitle())
                .status(Status.ACTIVE)
                .build();
    }

    public UserResponse toResponse(User domainUser) {
        return new UserResponse(
                domainUser.getId(),
                domainUser.getUsername(),
                domainUser.getEmail(),
                domainUser.getRole(),
                domainUser.getJobTitle(),
                domainUser.getStatus(),
                domainUser.getLastLogin(),
                domainUser.getCreatedAt(),
                domainUser.getPeople(),
                domainUser.isDeleted()
        );
    }

    public UserMinimalResponse toMinimalResponse(User domainUser) {
        return new UserMinimalResponse(
                domainUser.getId(),
                domainUser.getUsername(),
                domainUser.getEmail(),
                domainUser.getRole(),
                domainUser.getJobTitle()
        );
    }

}
