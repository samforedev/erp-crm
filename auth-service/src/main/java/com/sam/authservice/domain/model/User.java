package com.sam.authservice.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class User extends BaseModel {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean isActive;
}
