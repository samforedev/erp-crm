package com.sam.insuranceservice.application.dto.error;

import com.sam.insuranceservice.application.dto.common.Error;

public class UserErrorCatalog {
    public static final Error NOT_FOUND = Error.of("not_found", "The user not found");
}
