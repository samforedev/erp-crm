package com.sam.insuranceservice.domain.model.user;

import com.sam.insuranceservice.domain.model.BaseModel;
import com.sam.insuranceservice.domain.model.BasePeople;
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
    private BasePeople people;
    private String phoneNumber;
    private String jobTitle;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean isActive;
}
