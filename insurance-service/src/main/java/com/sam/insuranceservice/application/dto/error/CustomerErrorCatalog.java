package com.sam.insuranceservice.application.dto.error;

import com.sam.insuranceservice.application.dto.common.response.Error;

public class CustomerErrorCatalog {
    public final static Error ALREADY_EXIST = Error.of("already_exist", "The Customer already exists. ");
    public final static Error NOT_FOUND = Error.of("not_found", "The Customer not found. ");
    public final static Error ALREADY_STATUS= Error.of("already_status", "The Customer status already assigned. ");
    public final static Error ERROR_UPDATING = Error.of("error_updating", "Some error updating");
    public final static Error NOT_AGENT_FOUND = Error.of("not_agent_found", "Some error agent not found. ");
}
