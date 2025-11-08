package com.sam.insuranceservice.infraestructure.persistence.adapter;

import com.sam.insuranceservice.application.dto.user.GetUserByFilters;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.domain.model.user.User;
import com.sam.insuranceservice.domain.port.IUserRepositoryPort;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import com.sam.insuranceservice.infraestructure.persistence.mapper.UserMapper;
import com.sam.insuranceservice.infraestructure.persistence.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements IUserRepositoryPort {

    private final IUserJpaRepository _userJpaRepository;
    private final UserMapper _userMapper;

    private final String PEOPLE = "people";

    @Override
    public User save(User user) {
        UserEntity existingEntity = _userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User ID not found during save operation."));
        _userMapper.updateEntityFromDomain(user, existingEntity);

        UserEntity userUpdated = _userJpaRepository.save(existingEntity);
        return _userMapper.toDomain(userUpdated);
    }

    @Override
    public UUID deleteOne(UUID id) {
        UserEntity existingEntity = _userJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User ID not found during delete operation."));

        existingEntity.setStatus(Status.DELETED);
        existingEntity.setDeleted(true);
        existingEntity.setActive(false);
        existingEntity.setUpdatedAt(Instant.now());
        existingEntity = _userJpaRepository.save(existingEntity);
        return existingEntity.getId();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return _userJpaRepository.findByIdAndDeletedIsFalse(id)
                .map(_userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username, Status status) {
        return _userJpaRepository.findByUsernameAndDeletedIsFalseAndStatus(username, status)
                .map(_userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email, Status status) {
        return _userJpaRepository.findByEmailAndDeletedIsFalseAndStatus(email, status)
                .map(_userMapper::toDomain);
    }

    @Override
    public List<User> findAll(Status status) {
        return _userJpaRepository.findAllByDeletedIsFalseAndStatus(status)
                .stream()
                .map(_userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByFilters(GetUserByFilters request) {
        Specification<UserEntity> spec = buildSpecification(request);
        List<UserEntity> result = _userJpaRepository.findAll(spec);
        return result.stream()
                .map(_userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public int changeUserStatus(UUID id, Status status) {
         return _userJpaRepository.updateStatusByIdAndIsDeletedFalse(id, status);
    }

    private Specification<UserEntity> buildSpecification(GetUserByFilters filters) {
        Specification<UserEntity> spec = (root, query, cb) -> cb.equal(root.get("deleted"), false);

        if (filters.documentType() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get(PEOPLE).get("documentType"), filters.documentType())
            );
        }

        if (filters.documentNumber() != null && !filters.documentNumber().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get(PEOPLE).get("documentNumber"), "%" + filters.documentNumber() + "%")
            );
        }

        if (filters.firstName() != null && !filters.firstName().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get(PEOPLE).get("firstName")), "%" + filters.firstName().toLowerCase() + "%")
            );
        }

        if (filters.username() != null && !filters.username().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("username")), "%" + filters.username().toLowerCase() + "%")
            );
        }

        if (filters.email() != null && !filters.email().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("email")), "%" + filters.email().toLowerCase() + "%")
            );
        }

        if (filters.status() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("status"), "%" + filters.status() + "%")
            );
        }

        return spec;
    }

}
