package com.sam.authservice.infraestructure.persistence.mapper;

import com.sam.authservice.domain.model.User;
import com.sam.authservice.infraestructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleMapper _roleMapper;

    public UserEntity toEntity(User domainUser) {
        if (domainUser == null) return null;

        return UserEntity.builder()
                .username(domainUser.getUsername())
                .email(domainUser.getEmail())
                .password(domainUser.getPassword())
                .isActive(domainUser.isActive())
                .roles(domainUser.getRoles().stream()
                        .map(_roleMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setRoles(entity.getRoles().stream()
                .map(_roleMapper::toDomain)
                .collect(Collectors.toSet()));
        return user;
    }
}
