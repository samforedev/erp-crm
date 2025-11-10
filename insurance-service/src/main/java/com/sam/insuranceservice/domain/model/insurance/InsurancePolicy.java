package com.sam.insuranceservice.domain.model.insurance;

import com.sam.insuranceservice.domain.model.BaseModel;
import com.sam.insuranceservice.domain.model.enums.PolicyStatus;
import com.sam.insuranceservice.domain.model.enums.PolicyType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InsurancePolicy extends BaseModel {
    private String policyNumber;
    private UUID customerId;
    private UUID agentId;
    private PolicyType policyType;
    private PolicyStatus policyStatus;
    private LocalDate starDate;
    private LocalDate endDate;
}
