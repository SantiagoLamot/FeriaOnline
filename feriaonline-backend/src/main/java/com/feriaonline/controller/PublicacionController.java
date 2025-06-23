package com.feriaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PublicacionDTO crearPublicacion(@RequestPart PublicacionRequestDTO dto, @RequestPart("imagenes") List<MultipartFile> imagenes) {
        return publicacionService.crearPublicacion(dto, imagenes);
    }
}
