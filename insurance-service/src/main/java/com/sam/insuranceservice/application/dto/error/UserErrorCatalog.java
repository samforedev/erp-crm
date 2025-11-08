package com.sam.insuranceservice.application.dto.error;

import com.sam.insuranceservice.application.dto.common.response.Error;

public class UserErrorCatalog {
    public static final Error NOT_FOUND = Error.of("not_found", "The user not found");
    public static final Error ERROR_UPDATING = Error.of("error_updating", "Some error while updating the user");
    public static final Error ALREADY_ACTIVE = Error.of("already_active", "The user already active");
    public static final Error ALREADY_INACTIVE = Error.of("already_inactive", "The user already inactive");
}
