package com.feriaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.feriaonline.dto.PublicacionRequestDTO;
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

    @PostMapping
    public PublicacionDTO crearPublicacion(@RequestBody PublicacionRequestDTO dto) {
        return publicacionService.crearPublicacion(dto);
    }
}
