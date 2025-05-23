package com.feriaonline.authentication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${application.properties.jwt.secret-key}")
    private String secretKey;

    @Value("${application.properties.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.properties.jwt.refresh-token.expiration}")
    private long refreshExpiration;
}
