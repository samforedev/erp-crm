package com.sam.insuranceservice.application.dto.user;

import com.sam.insuranceservice.application.dto.common.StatusCommons;
public record GetByUsernameOrEmail(
        String usernameOrEmail,
        StatusCommons statusCommons
) {
}
