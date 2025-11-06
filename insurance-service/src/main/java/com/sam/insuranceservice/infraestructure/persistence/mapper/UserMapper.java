package com.sam.insuranceservice.infraestructure.persistence.mapper;

import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final CommonsMapper _commonsMapper;

    public UserEntity toJpaEntity(User domainUser) {
        if (domainUser == null) return null;
        return UserEntity.builder()
                .id(domainUser.getId())
                .createdAt(domainUser.getCreatedAt())
                .updatedAt(domainUser.getUpdatedAt())
                .isDeleted(domainUser.isDeleted())
                .people(_commonsMapper.toEmbeddable(domainUser.getPeople()))
                .username(domainUser.getUsername())
                .email(domainUser.getEmail())
                .password(domainUser.getPassword())
                .role(domainUser.getRole())
                .jobTitle(domainUser.getJobTitle())
                .lastLogin(domainUser.getLastLogin())
                .status(domainUser.getStatus())
                .build();
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setId(entity.getId());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setDeleted(entity.isDeleted());
        user.setPeople(_commonsMapper.toDomainRecord(entity.getPeople()));
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        user.setJobTitle(entity.getJobTitle());
        user.setLastLogin(entity.getLastLogin());
        user.setStatus(entity.getStatus());

        return user;
    }
}
