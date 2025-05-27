package com.feriaonline.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feriaonline.entidades.Usuario;

public interface TokenRepository extends JpaRepository<Token, Integer>  {

    List<Token> findByIsExpiredFalseAndIsRevokedFalseAndUsuario(Usuario usuario);
}
