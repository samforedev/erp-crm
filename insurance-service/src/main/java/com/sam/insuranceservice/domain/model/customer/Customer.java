package com.sam.insuranceservice.domain.model.customer;

import com.sam.insuranceservice.domain.model.BaseModel;
import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Customer extends BaseModel {
    private BasePeople people;
    private String email;
    private String phoneNumber;
    private UUID assignedAgentId;
    private CustomerStatus customerStatus;
    private Status status;

}
