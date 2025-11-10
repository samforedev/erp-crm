package com.sam.authservice.domain.model;

import com.sam.authservice.domain.enums.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class User extends BaseModel {
    private String firstName;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate birthDate;
    private String phoneNumber;
    private String jobTitle;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean isActive;
}
