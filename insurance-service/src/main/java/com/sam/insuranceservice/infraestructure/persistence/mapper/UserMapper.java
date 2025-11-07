package com.sam.insuranceservice.infraestructure.persistence.mapper;

import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        user.setStatus(entity.getStatus());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setDeleted(entity.isDeleted());

        return user;
    }
}
