package com.sam.insuranceservice.domain.model.user;

import com.sam.insuranceservice.domain.model.BaseModel;
import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.Role;
import com.sam.insuranceservice.domain.model.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class User extends BaseModel {
    private BasePeople people;
    private String phoneNumber;
    private String jobTitle;
    private Status status;
}
