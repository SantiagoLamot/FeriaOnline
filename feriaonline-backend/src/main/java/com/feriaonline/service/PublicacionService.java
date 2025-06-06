package com.feriaonline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.entidades.EstadoPublicacion;
import com.feriaonline.entidades.ImagenPublicacion;
import com.feriaonline.entidades.Publicacion;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.dto.PublicacionRequestDTO;
import com.feriaonline.repository.PublicacionRepository;
import com.feriaonline.repository.UsuarioRepository;

@Service
public class PublicacionService {

    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findByEstado(EstadoPublicacion.Activo)
                .stream()
                .map(PublicacionDTO::new)
                .collect(Collectors.toList());
    }

    public List<PublicacionDTO> misPublicaciones() {
        return publicacionRepository.findByUsuarioVendedor_Id(jwtService.obtenerIdUsuarioAutenticado())
                .stream()
                .map(PublicacionDTO::new)
                .collect(Collectors.toList());
    }

    public PublicacionDTO crearPublicacion(PublicacionRequestDTO dto) {
        int userId = jwtService.obtenerIdUsuarioAutenticado();
        Usuario usuarioVendedor = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Publicacion publicacion = Publicacion.builder()
            .nombreProducto(dto.getNombreProducto())
            .descripcion(dto.getDescripcion())
            .precio(dto.getPrecio())
            .estado(EstadoPublicacion.Activo)
            .usuarioVendedor(usuarioVendedor)
            .build();

        if (dto.getImagenesBase64() != null) {
            publicacion.setImagenes(dto.getImagenesBase64().stream()
                .map(base64 -> ImagenPublicacion.builder()
                    .url(base64) // Asumiendo que las imágenes son URLs o base64
                    .publicacion(publicacion)
                    .build())
                .collect(Collectors.toList()));
        }

        Publicacion guardada = publicacionRepository.save(publicacion);
        return new PublicacionDTO(guardada);
        
    }
}