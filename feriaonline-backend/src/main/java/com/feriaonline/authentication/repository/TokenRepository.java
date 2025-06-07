package com.feriaonline.authentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feriaonline.entidades.Usuario;

public interface TokenRepository extends JpaRepository<Token, Integer>  {

    List<Token> findByUsuarioAndIsExpiredFalseAndIsRevokedFalse(Usuario usuario);
    Optional<Token> findByToken(String token);
    Optional<Token> findByTokenAndIsRevokedFalseAndIsExpiredFalse(String token);
}
