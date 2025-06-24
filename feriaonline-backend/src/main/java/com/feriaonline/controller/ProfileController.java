package com.feriaonline.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feriaonline.dto.UsuarioEditarPerfilDTO;
import com.feriaonline.dto.UsuarioPerfilDTO;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.entidadesDTO.TransaccionDTO;
import com.feriaonline.service.PublicacionService;
import com.feriaonline.service.UsuarioService;
import com.feriaonline.service.VentasService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private VentasService ventasService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/publicaciones")
    public ResponseEntity<?> misPublicaciones() {
        try {
            List<PublicacionDTO> publicaciones = publicacionService.misPublicaciones();
            if (publicaciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(publicaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener publicaciones");
        }
    }

    @GetMapping("/miperfil")
    public ResponseEntity<?> obtenerMiPerfil() {
        try {
            UsuarioPerfilDTO perfil = usuarioService.miPerfil();
            return ResponseEntity.ok(perfil);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener perfil");
        }
    }

    @PutMapping("/editarperfil")
    public ResponseEntity<?> editarPerfil(@RequestBody UsuarioEditarPerfilDTO dto) {
        try {
            UsuarioPerfilDTO perfilEditado = usuarioService.editarPerfil(dto);
            return ResponseEntity.ok(perfilEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos para editar perfil");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar perfil");
        }
    }

    @GetMapping("/ventas")
    public ResponseEntity<?> misVentas() {
        try {
            List<TransaccionDTO> ventas = ventasService.misVentas();
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener ventas");
        }
    }

    @GetMapping("/compras")
    public ResponseEntity<?> misCompras() {
        try {
            List<TransaccionDTO> compras = ventasService.misCompras();
            return ResponseEntity.ok(compras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener compras");
        }
    }
}
