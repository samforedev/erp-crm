package com.sam.insuranceservice.infraestructure.persistence.adapter;

import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import com.sam.insuranceservice.infraestructure.persistence.mapper.UserMapper;
import com.sam.insuranceservice.infraestructure.persistence.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements IUserRepositoryPort {

    private final IUserJpaRepository _userJpaRepository;
    private final UserMapper _userMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return _userJpaRepository.findByUsernameAndDeletedIsFalse(username)
                .map(_userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return _userJpaRepository.findByEmailAndDeletedIsFalse(email)
                .map(_userMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return _userJpaRepository.findAllByDeletedIsFalse()
                .stream()
                .map(_userMapper::toDomain)
                .collect(Collectors.toList());
    }
}
