package com.sam.authservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class Role extends BaseModel {
    private String name;
    private Set<Permission> permissions;

}
