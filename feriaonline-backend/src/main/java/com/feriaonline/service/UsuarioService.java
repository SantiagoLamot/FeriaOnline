package com.feriaonline.service;
import org.springframework.stereotype.Service;
import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.dto.UsuarioPerfilDTO;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public UsuarioPerfilDTO miPerfil() {
        int idUsuario = jwtService.obtenerIdUsuarioAutenticado();
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        UsuarioPerfilDTO dto = new UsuarioPerfilDTO();
        dto.setId(usuario.getId());
        dto.setNombreDeUsuario(usuario.getNombreDeUsuario());
        dto.setCorreo(usuario.getCorreo());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setFotoDePerfil(usuario.getFotoDePerfil());

        return dto;
    }
}
