package com.sam.insuranceservice.domain.port;

import com.sam.insuranceservice.domain.model.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepositoryPort {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
