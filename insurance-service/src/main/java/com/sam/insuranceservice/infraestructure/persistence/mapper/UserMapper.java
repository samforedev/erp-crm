package com.sam.insuranceservice.infraestructure.persistence.mapper;

import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.user.Permission;
import com.sam.insuranceservice.domain.model.user.Role;
import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.PermissionEntity;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.RoleEntity;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final CommonsMapper _commonsMapper;

    public UserEntity toEntity(User domainUser) {
        if (domainUser == null) return null;
        return UserEntity.builder()
                .id(domainUser.getId())
                .people(_commonsMapper.toEmbeddable(domainUser.getPeople()))
                .phoneNumber(domainUser.getPhoneNumber())
                .jobTitle(domainUser.getJobTitle())
                .username(domainUser.getUsername())
                .email(domainUser.getEmail())
                .password(domainUser.getPassword())
                .isActive(domainUser.isActive())
                .roles(domainUser.getRoles().stream()
                        .map(this::roleToEntity)
                        .collect(Collectors.toSet()))
                .status(domainUser.getStatus())
                .createdAt(domainUser.getCreatedAt())
                .updatedAt(domainUser.getUpdatedAt())
                .deleted(domainUser.isDeleted())
                .build();
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setId(entity.getId());
        user.setPeople(_commonsMapper.toDomainRecord(entity.getPeople()));
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setJobTitle(entity.getJobTitle());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRoles(entity.getRoles().stream()
                .map(this::roleToDomain)
                .collect(Collectors.toSet()));
        user.setActive(entity.isActive());
        user.setStatus(entity.getStatus());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setDeleted(entity.isDeleted());

        return user;
    }

    public void updateEntityFromDomain(User source, UserEntity target) {
        target.setPhoneNumber(source.getPhoneNumber());
        target.setJobTitle(source.getJobTitle());
        target.setUsername(source.getUsername());
        target.setUpdatedAt(Instant.now());

        BasePeople sourcePeople = source.getPeople();

        target.getPeople().setFirstName(sourcePeople.firstName());
        target.getPeople().setLastName(sourcePeople.lastName());
        target.getPeople().setDocumentType(sourcePeople.documentType());
        target.getPeople().setDocumentNumber(sourcePeople.documentNumber());
        target.getPeople().setBirthDate(sourcePeople.birthDate());
    }

    private Role roleToDomain(RoleEntity entity) {
        if (entity == null) return null;
        Role role = new Role();
        role.setId(entity.getId());
        role.setName(entity.getName());
        role.setPermissions(entity.getPermissions().stream()
                .map(this::permissionToDomain)
                .collect(Collectors.toSet()));
        return role;
    }

    private RoleEntity roleToEntity(Role role) {
        if (role == null) return null;
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions().stream()
                        .map(this::permissionToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    private Permission permissionToDomain(PermissionEntity entity) {
        if (entity == null) return null;
        Permission permission = new Permission();
        permission.setId(entity.getId());
        permission.setName(entity.getName());
        return permission;
    }

    private PermissionEntity permissionToEntity(Permission permission) {
        if (permission == null) return null;
        return PermissionEntity.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

}
