package com.sam.insuranceservice.domain.port;

import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.domain.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepositoryPort {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username, Status status);
    Optional<User> findByEmail(String email, Status status);
    List<User> findAll(Status status);
    List<User> findAllByFilters(FiltersCommons request);
    int changeUserStatus(UUID id, Status status);
    UUID deleteOne(UUID id);
}
