package com.sam.insuranceservice.infraestructure.persistence.adapter;

import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import com.sam.insuranceservice.infraestructure.persistence.mapper.UserMapper;
import com.sam.insuranceservice.infraestructure.persistence.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements IUserRepositoryPort {

    private final IUserJpaRepository _userJpaRepository;
    private final UserMapper _userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = _userMapper.toEntity(user);
        UserEntity savedUser = _userJpaRepository.save(entity);
        return _userMapper.toDomain(savedUser);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByDocumentNumber(String documentNumber) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public UUID update(UUID id, User user) {
        return null;
    }

    @Override
    public UUID delete(UUID id) {
        return null;
    }
}
