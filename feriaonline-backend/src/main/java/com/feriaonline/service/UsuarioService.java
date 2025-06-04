package com.feriaonline.service;

import org.springframework.stereotype.Service;
import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.dto.UsuarioEditarPerfilDTO;
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

    public UsuarioPerfilDTO editarPerfil(UsuarioEditarPerfilDTO dto) {
        int idUsuario = jwtService.obtenerIdUsuarioAutenticado();

        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        // Validar que el nombre de usuario no esté en uso
        /*if (dto.getNombreDeUsuario() != null &&
            !dto.getNombreDeUsuario().equals(usuario.getNombreDeUsuario())) {

                boolean nombreEnUso = usuarioRepository
                    .findByNombreDeUsuario(dto.getNombreDeUsuario())
                    .filter(u -> !u.getId().equals(idUsuario))
                    .isPresent();
                
                if (nombreEnUso) {
                    throw new RuntimeException("El nombre de usuario ya está en uso");
                }

                usuario.setNombreDeUsuario(dto.getNombreDeUsuario());
            }*/

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setFotoDePerfil(dto.getFotoDePerfil());

        usuarioRepository.save(usuario);

        UsuarioPerfilDTO perfilActualizado = new UsuarioPerfilDTO();
        perfilActualizado.setId(usuario.getId());
        perfilActualizado.setNombreDeUsuario(usuario.getNombreDeUsuario());
        perfilActualizado.setCorreo(usuario.getCorreo());
        perfilActualizado.setNombre(usuario.getNombre());
        perfilActualizado.setApellido(usuario.getApellido());
        perfilActualizado.setFotoDePerfil(usuario.getFotoDePerfil());

        return perfilActualizado;
    }
}
