package com.sam.insuranceservice.domain.model.user;

import com.sam.insuranceservice.domain.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Permission extends BaseModel {
    private String name;
}