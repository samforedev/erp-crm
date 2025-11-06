package com.sam.insuranceservice.application.dto.common;

import java.util.Map;

public record Error(
        String errorCode,
        String errorMessage,
        Map<String, String> details
) {
    public static Error of(String errorCode, String errorMessage) {
        return new Error(errorCode, errorMessage, null);
    }
    public static Error of(String errorCode, String errorMessage, Map<String, String> details) {
        return new Error(errorCode, errorMessage, details);
    }
}
