package com.feriaonline.service;
import org.springframework.stereotype.Service;

import com.feriaonline.authentication.controller.RegisterRequest;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(RegisterRequest request) {

        if(usuarioRepository.findByNombreDeUsuario(request.nombreDeUsuario()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
        }

        if(usuarioRepository.findByCorreo(request.correo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya esta en uso");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreDeUsuario(request.nombreDeUsuario()); 
        nuevoUsuario.setCorreo(request.correo()); 
        nuevoUsuario.setContrasena(request.contrasena());
        nuevoUsuario.setNombre(request.nombre());
        nuevoUsuario.setApellido(request.apellido());
        nuevoUsuario.setFotoDePerfil(null); //A futuro poner una imagen por defecto

        return usuarioRepository.save(nuevoUsuario);
    }
}
