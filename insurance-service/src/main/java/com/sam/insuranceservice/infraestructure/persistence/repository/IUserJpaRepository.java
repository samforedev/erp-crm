package com.sam.insuranceservice.infraestructure.persistence.repository;

import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserJpaRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByIdAndDeletedIsFalse(UUID id);
    Optional<UserEntity> findByUsernameAndDeletedIsFalseAndStatus(String username, Status status);
    Optional<UserEntity> findByEmailAndDeletedIsFalseAndStatus(String email, Status status);
    List<UserEntity> findAllByDeletedIsFalseAndStatus(Status status);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.status = :status " +
            "WHERE u.id = :id AND u.deleted = false")
    int updateStatusByIdAndIsDeletedFalse(
            @Param("id") UUID id,
            @Param("status") Status status
    );
}
