package com.feriaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.service.PublicacionService;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/publicaciones")
    public List<PublicacionDTO> misPublicaciones(){
        return publicacionService.misPublicaciones();
    }
}
