package com.feriaonline.authentication.service;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.feriaonline.entidades.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String generateToken(final Usuario user) {
        return buildToken(user, jwtExpiration);
    }

    // Genera un refresh token con mayor duración
    public String generateRefreshToken(final Usuario user) {
        return buildToken(user, refreshExpiration);
    }

    // Construye un token JWT con claims, tiempo de expiración y firma
    private String buildToken(final Usuario user, final long expiration) {
        return Jwts
                .builder()
                .claims(Map.of("nombre", user.getNombre()))
                .subject(user.getCorreo())
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .expiration(new Date(System.currentTimeMillis() + expiration)) // Fecha de expiración
                .signWith(getSignInKey()) // Firma el token con la clave secreta
                .compact(); // Genera el JWT como String
    }

    private SecretKey getSignInKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Decodifica el string base64
        return Keys.hmacShaKeyFor(keyBytes); // Genera la clave HMAC
    }

}
