package com.feriaonline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.feriaonline.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    
    private final UsuarioRepository repository;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define BCrypt como algoritmo para codificar y verificar contraseñas
        return new BCryptPasswordEncoder();
    }
}
