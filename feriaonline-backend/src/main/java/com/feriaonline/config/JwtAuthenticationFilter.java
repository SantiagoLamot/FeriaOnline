package com.feriaonline.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.feriaonline.authentication.repository.TokenRepository;
import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UsuarioRepository userRepository;

    @Override
protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
) throws ServletException, IOException {

    System.out.println("[DEBUG] → Filtro JWT ejecutado");

    if (request.getServletPath().contains("/api/auth")) {
        System.out.println("[DEBUG] → Ruta pública, continúa sin filtro");
        filterChain.doFilter(request, response);
        return;
    }

    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        System.out.println("[DEBUG] → No hay Authorization o no es Bearer");
        filterChain.doFilter(request, response);
        return;
    }

    final String jwt = authHeader.substring(7);

    String username;
    try {
        username = jwtService.extractUsername(jwt);
        System.out.println("[DEBUG] → Usuario extraído del token: " + username);
    } catch (Exception e) {
        System.out.println("[ERROR] → Fallo al extraer username del token: " + e.getMessage());
        filterChain.doFilter(request, response);
        return;
    }

    final Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
    if (username == null || currentAuth != null) {
        System.out.println("[DEBUG] → Usuario nulo o ya autenticado");
        filterChain.doFilter(request, response);
        return;
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    System.out.println("[DEBUG] → UserDetails cargado: " + userDetails.getUsername());

    final boolean isTokenValidInDb = tokenRepository.findByToken(jwt)
            .map(token -> !token.getIsExpired() && !token.getIsRevoked())
            .orElse(false);

    if (!isTokenValidInDb) {
        System.out.println("[DEBUG] → El token está expirado o revocado en la DB");
        filterChain.doFilter(request, response);
        return;
    }

    final Optional<Usuario> user = userRepository.findByNombreDeUsuario(username);

    if (user.isPresent()) {
        boolean tokenValido = jwtService.isTokenValidUserName(jwt, user.get());
        System.out.println("[DEBUG] → ¿El token es válido para el usuario?: " + tokenValido);
        if (tokenValido) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("[DEBUG] → Autenticación configurada en el contexto de seguridad");
        } else {
            System.out.println("[DEBUG] → El token no es válido para el usuario");
        }
    } else {
        System.out.println("[DEBUG] → Usuario no encontrado en la base de datos");
    }

    filterChain.doFilter(request, response);
}
}
