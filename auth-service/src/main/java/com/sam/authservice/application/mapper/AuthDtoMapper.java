package com.sam.authservice.application.mapper;

import com.sam.authservice.application.dto.RegisterRequest;
import com.sam.authservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthDtoMapper {

    public User toDomain(RegisterRequest request){
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .roles(Collections.emptySet())
                .isActive(true)
                .build();
    }
}
