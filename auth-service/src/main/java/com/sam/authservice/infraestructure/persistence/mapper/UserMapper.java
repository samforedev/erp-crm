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
                .id(domainUser.getId())
                .firstName(domainUser.getFirstName())
                .lastName(domainUser.getLastName())
                .documentType(domainUser.getDocumentType())
                .documentNumber(domainUser.getDocumentNumber())
                .birthDate(domainUser.getBirthDate())
                .phoneNumber(domainUser.getPhoneNumber())
                .jobTitle(domainUser.getJobTitle())
                .username(domainUser.getUsername())
                .email(domainUser.getEmail())
                .password(domainUser.getPassword())
                .isActive(domainUser.isActive())
                .roles(domainUser.getRoles().stream()
                        .map(_roleMapper::toEntity)
                        .collect(Collectors.toSet()))
                .status(domainUser.getStatus())
                .build();
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setDocumentType(entity.getDocumentType());
        user.setDocumentNumber(entity.getDocumentNumber());
        user.setBirthDate(entity.getBirthDate());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setJobTitle(entity.getJobTitle());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setActive(entity.isActive());
        user.setRoles(entity.getRoles().stream()
                .map(_roleMapper::toDomain)
                .collect(Collectors.toSet()));
        user.setStatus(entity.getStatus());
        return user;
    }
}
