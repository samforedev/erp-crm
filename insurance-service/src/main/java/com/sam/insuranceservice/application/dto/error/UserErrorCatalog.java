package com.sam.insuranceservice.application.dto.error;

import com.sam.insuranceservice.application.dto.common.Error;

public class UserErrorCatalog {
    public static final Error ALREADY_EXISTS = Error.of("already_exists", "The user already exists");
    public static final Error ERROR_CREATING = Error.of("error_creating", "Some error occurred while creating the user");
}
