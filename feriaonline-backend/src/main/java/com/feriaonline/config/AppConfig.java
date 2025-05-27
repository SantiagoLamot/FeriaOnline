package com.feriaonline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    
    private final UsuarioRepository repo; 

    @Bean
    public UserDetailsService userDetailsService() {
        return username->{
            final Usuario user = repo.findByNombreDeUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getNombreDeUsuario())
                        .password(user.getContrasena())
                        .build();
        };
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define BCrypt como algoritmo para codificar y verificar contraseñas
        return new BCryptPasswordEncoder();
    }

    

    
}
