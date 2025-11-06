package com.sam.insuranceservice.domain.port;

import com.sam.insuranceservice.domain.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepositoryPort {
    // Crud Base
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    UUID update(UUID id, User user);
    UUID delete(UUID id);
}
