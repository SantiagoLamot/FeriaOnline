package com.feriaonline.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.dto.PublicacionRequestDTO;
import com.feriaonline.entidades.EstadoPublicacion;
import com.feriaonline.entidades.ImagenPublicacion;
import com.feriaonline.entidades.Publicacion;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.repository.ImagenRepository;
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

    @Autowired
    private ImagenRepository imagenRepository;

    @Value("${app.upload.dir:${user.dir}/uploads}")
    private String uploadDir;

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

    public PublicacionDTO crearPublicacion(PublicacionRequestDTO dto, List<MultipartFile> imagenes) {
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
        Publicacion guardada = publicacionRepository.save(publicacion);

        for (MultipartFile file : imagenes) {
            if (!file.isEmpty()) {
                try {
                    String extension = Objects.requireNonNull(file.getOriginalFilename())
                            .substring(file.getOriginalFilename().lastIndexOf("."));
                    String fileName = UUID.randomUUID().toString() + extension;
                    Path filePath = Paths.get(uploadDir, fileName);
                    Files.createDirectories(filePath.getParent());
                    Files.write(filePath, file.getBytes());
                    System.out.println("RUTA :"+ filePath);

                    ImagenPublicacion imagen = new ImagenPublicacion();
                    imagen.setUrl("/uploads/" + fileName);
                    imagen.setPublicacion(guardada);
                    imagenRepository.save(imagen);

                } catch (Exception e) {
                    throw new RuntimeException("Error al guardar imagen", e);
                }
            }
        }
        return new PublicacionDTO(guardada);

    }
}