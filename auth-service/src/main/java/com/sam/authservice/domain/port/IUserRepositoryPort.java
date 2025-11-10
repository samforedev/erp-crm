package com.sam.authservice.domain.port;

import com.sam.authservice.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepositoryPort {
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
