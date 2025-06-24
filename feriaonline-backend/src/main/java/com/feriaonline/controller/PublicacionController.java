package com.feriaonline.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.feriaonline.dto.PublicacionRequestDTO;
import com.feriaonline.entidadesDTO.ImagenPublicacionDTO;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.service.PublicacionService;

@RestController
@RequestMapping("/publicaciones")
@CrossOrigin(origins = "*") // permitir requests del frontend
public class PublicacionController {

    @Autowired
    PublicacionService publicacionService;

    @GetMapping()
    public List<PublicacionDTO> ObtenerTodasLasPublicaciones() {
        return publicacionService.obtenerTodasLasPublicaciones();
    }

    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearPublicacion(
            @RequestPart PublicacionRequestDTO dto,
            @RequestPart("imagenes") List<MultipartFile> imagenes) {
        try {
            PublicacionDTO creada = publicacionService.crearPublicacion(dto, imagenes);
            return ResponseEntity.ok(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear publicación");
        }
    }

    @DeleteMapping("/imagen")
    public ResponseEntity<?> eliminarImagen(@RequestBody ImagenPublicacionDTO dto) {
        try {
            publicacionService.eliminarImagen(dto);
            return ResponseEntity.ok("Imagen eliminada correctamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagen no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar imagen");
        }
    }

    @PostMapping(value = "/cargar/imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> cargarImagen(@RequestPart PublicacionDTO dto,
            @RequestPart("imagenes") List<MultipartFile> imagenes) {
        try {
            publicacionService.cargarImagenes(dto, imagenes);
            return ResponseEntity.ok("Imagens actualizadas correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cargar imagenes" + e.toString());
        }
    }

}
