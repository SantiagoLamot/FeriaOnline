package com.feriaonline.authentication.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feriaonline.authentication.controller.LoginRequest;
import com.feriaonline.authentication.controller.RegisterRequest;
import com.feriaonline.authentication.controller.TokenResponse;
import com.feriaonline.authentication.repository.Token;
import com.feriaonline.authentication.repository.TokenRepository;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenResponse registrarUsuario(final RegisterRequest request) {

        if(usuarioRepository.findByNombreDeUsuario(request.nombreDeUsuario()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
        }

        if(usuarioRepository.findByCorreo(request.correo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya esta en uso");
        }

        final Usuario usuario = Usuario.builder()
                        .nombre(request.nombre())
                        .apellido(request.apellido())
                        .correo(request.correo())
                        .contrasena(passwordEncoder.encode(request.contrasena()))
                        .nombreDeUsuario(request.nombreDeUsuario())
                        .fotoDePerfil(null)
                        .build();
        final Usuario usuarioGuardado = usuarioRepository.save(usuario);

        final String jwtToken = jwtService.generateToken(usuarioGuardado);
        final String refreshToken = jwtService.generateRefreshToken(usuarioGuardado);

        saveUserToken(usuarioGuardado, jwtToken);

        return new TokenResponse(jwtToken, refreshToken);
    }

    public TokenResponse login(LoginRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        Usuario usuario = usuarioRepository.findByNombreDeUsuario(request.getUsername())
            .orElseThrow();
        String token = jwtService.generateToken(usuario);
        String tokenR = jwtService.generateRefreshToken(usuario);
        revocarTokenUsuario(usuario);
        saveUserToken(usuario, token);
        return new TokenResponse(token, tokenR);
    }

    private void saveUserToken(Usuario user, String jwtToken) {
        final Token token = Token.builder()
                .usuario(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revocarTokenUsuario(Usuario usuario){
        final List<Token> tokenValidos = tokenRepository.findByUsuarioAndIsExpiredFalseAndIsRevokedFalse(usuario);
        if(!tokenValidos.isEmpty()){
            for(Token t : tokenValidos){
                t.setIsExpired(true);
                t.setIsRevoked(true);
            }
            tokenRepository.saveAll(tokenValidos);
        }
    }

    public TokenResponse refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token de autenticación no válido");
        }

        final String refreshToken = authentication.substring(7);

        final String email = jwtService.extractEmail(refreshToken);
        if (email == null) {
            return null; // O lanzar una excepción si se prefiere
        }

        final Usuario usuario = this.usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, usuario);
        if (!isTokenValid) {
            return null; // O lanzar una excepción si se prefiere
        }

        final String accesToken = jwtService.generateRefreshToken(usuario);

        revocarTokenUsuario(usuario);
        saveUserToken(usuario, accesToken);

        return new TokenResponse(accesToken, refreshToken);
    }

}
