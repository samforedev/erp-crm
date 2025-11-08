package com.sam.authservice.infraestructure.util;

import com.sam.authservice.application.dto.common.Response;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseValidator {
    public static <T> Object validator(Response<T> response) {
        var object = new HashMap<String, Object>();
        if (response.success()) {
            object.put("isSuccess", true);
            object.put("data", response.data());
            object.put("message", response.message());
            return object;
        }

        object.put("isSuccess", false);
        object.put("errorCode", response.error().errorCode());
        object.put("errorMessage", response.error().errorMessage());

        return object;
    }

}
