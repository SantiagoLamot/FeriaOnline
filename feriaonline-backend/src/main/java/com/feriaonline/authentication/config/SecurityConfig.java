package com.feriaonline.authentication.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.feriaonline.authentication.repository.Token;
import com.feriaonline.authentication.repository.TokenRepository;

import lombok.var;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final TokenRepository tokenRepository;

    SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider, TokenRepository tokenRepository) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.tokenRepository = tokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // permite registrar y loguear sin auth
                .anyRequest().authenticated()
            )
            .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout -> 
            logout.logoutUrl("/api/auth/logout")
                .addLogoutHandler((request, response, authentication)->{
                    final var authHeader = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
                    logout(authHeader);
                })
                .logoutSuccessHandler((request, response,authentication)->
                    SecurityContextHolder.clearContext())
            );
        return http.build();
    }

    private void logout(final String token){
        if(token==null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("Token invalido");
        }
        final String jwt = token.substring(7);
        final Token foundToken = tokenRepository.findByTokenAndIsRevokedFalseAndIsExpiredFalse(jwt)
            .orElseThrow(()->new IllegalArgumentException("Token Invalido"));
        foundToken.setIsExpired(true);
        foundToken.setIsRevoked(true);
        tokenRepository.save(foundToken);
    }
}
