package com.feriaonline.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feriaonline.authentication.controller.RegisterRequest;
import com.feriaonline.authentication.controller.TokenResponse;
import com.feriaonline.authentication.repository.Token;
import com.feriaonline.authentication.repository.TokenRepository;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

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
}
