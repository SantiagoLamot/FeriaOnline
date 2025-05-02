package com.feriaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.service.PublicacionService;


@Controller
@RequestMapping("/api/hospedajes")
@CrossOrigin(origins = "*") // permitir requests del frontend
public class PublicacionController {
    
    @Autowired
    PublicacionService publicacionService;

    @GetMapping()
    public List<PublicacionDTO> getMethodName(@RequestParam String param) {
        return publicacionService.obtenerTodasLasPublicaciones();
    }
    
}
