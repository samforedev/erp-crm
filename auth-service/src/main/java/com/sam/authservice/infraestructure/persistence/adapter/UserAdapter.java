package com.sam.authservice.infraestructure.persistence.adapter;

import com.sam.authservice.domain.model.User;
import com.sam.authservice.domain.port.IUserRepositoryPort;
import com.sam.authservice.infraestructure.persistence.entity.UserEntity;
import com.sam.authservice.infraestructure.persistence.mapper.UserMapper;
import com.sam.authservice.infraestructure.persistence.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements IUserRepositoryPort {

    private final IUserJpaRepository _userJpaRepository;
    private final UserMapper _userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = _userMapper.toEntity(user);
        UserEntity savedEntity = _userJpaRepository.save(entity);
        return _userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return _userJpaRepository.findByUsername(username)
                .map(_userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return _userJpaRepository.findByEmail(email)
                .map(_userMapper::toDomain);
    }
}
