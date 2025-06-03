package com.feriaonline.authentication.service;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

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

    private UsuarioRepository usuarioRepository;

    public JwtService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

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
                .subject(user.getNombreDeUsuario())
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .expiration(new Date(System.currentTimeMillis() + expiration)) // Fecha de expiración
                .signWith(getSignInKey()) // Firma el token con la clave secreta
                .compact(); // Genera el JWT como String
    }

    private SecretKey getSignInKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Decodifica el string base64
        return Keys.hmacShaKeyFor(keyBytes); // Genera la clave HMAC
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); // Extrae el sujeto (correo) del token
    }

    public boolean isTokenValid(String token, Usuario usuario) {
        final String email = extractEmail(token);
        return (email.equals(usuario.getCorreo()) && !isTokenExpired(token));
    }
    public boolean isTokenValidUserName(String token, Usuario usuario) {
        final String username = extractEmail(token);
        return (username.equals(usuario.getNombreDeUsuario()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration(); // Extrae la fecha de expiración del token
    }

    public String extractUsername(String token) {
        return Jwts.parser() // Crea el parser
                .verifyWith(getSignInKey()) // Verifica usando la clave secreta
                .build()
                .parseSignedClaims(token) // Parsea y valida el JWT
                .getPayload()
                .getSubject(); // El "subject" es el email del usuario en este caso
    }

    public int obtenerIdUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) auth.getPrincipal();
        return userId;
    }

}
