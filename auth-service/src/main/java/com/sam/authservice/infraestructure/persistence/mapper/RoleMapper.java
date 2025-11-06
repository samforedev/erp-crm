package com.sam.authservice.infraestructure.persistence.mapper;

import com.sam.authservice.domain.model.Role;
import com.sam.authservice.infraestructure.persistence.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final PermissionMapper _permissionMapper;

    public Role toDomain(RoleEntity entity) {
        if (entity == null) return null;
        Role role = new Role();
        role.setId(entity.getId());
        role.setName(entity.getName());
        role.setPermissions(entity.getPermissions().stream()
                .map(_permissionMapper::toDomain)
                .collect(Collectors.toSet()));
        return role;
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) return null;
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions().stream()
                        .map(_permissionMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

}
