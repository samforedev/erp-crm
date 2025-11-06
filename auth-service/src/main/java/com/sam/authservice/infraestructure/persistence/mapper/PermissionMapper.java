package com.sam.authservice.infraestructure.persistence.mapper;

import com.sam.authservice.domain.model.Permission;
import com.sam.authservice.infraestructure.persistence.entity.PermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public Permission toDomain(PermissionEntity entity) {
        if (entity == null) return null;
        Permission permission = new Permission();
        permission.setName(entity.getName());
        return permission;
    }

    public PermissionEntity toEntity(Permission permission) {
        if (permission == null) return null;
        return PermissionEntity.builder()
                .name(permission.getName())
                .build();
    }

}
