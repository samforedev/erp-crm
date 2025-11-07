package com.sam.authservice.application.mapper;

import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.domain.enums.DocumentType;
import com.sam.authservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDtoMapper {

    public User toDomain(RegisterRequest request){
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .documentType(request.documentType())
                .documentNumber(request.documentNumber())
                .birthDate(request.birthDate())
                .phoneNumber(request.phoneNumber())
                .jobTitle(request.jobTitle())
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .roles(Collections.emptySet())
                .isActive(true)
                .build();
    }
}
