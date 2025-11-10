package com.sam.insuranceservice.infraestructure.util;

import com.sam.insuranceservice.application.dto.common.FiltersCommons;
import com.sam.insuranceservice.infraestructure.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class BuildsSpecification {

    public static final String PEOPLE = "people";

    public static<T> Specification<T> buildFiltersCommons(FiltersCommons filters) {
        Specification<T> spec = (root, query, cb) -> cb.equal(root.get("deleted"), false);

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
