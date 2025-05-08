package com.feriaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feriaonline.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer > {
    
}
