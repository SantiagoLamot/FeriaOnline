package com.feriaonline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feriaonline.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer > {
    
    Optional<Usuario>findByNombreDeUsuario(String nombreDeUsuario);

    Optional<Usuario>findByCorreo(String correo);
}
