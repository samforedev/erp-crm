package com.sam.authservice.domain.port;

import com.sam.authservice.domain.model.User;

import java.util.Map;

public interface IJwtServicePort {
    String generateToken(User user);
    String generateToken(Map<String, Object> extraClaims, User user);
}
