package com.feriaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feriaonline.dto.UsuarioPerfilDTO;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.service.PublicacionService;
import com.feriaonline.service.UsuarioService;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/publicaciones")
    public List<PublicacionDTO> misPublicaciones(){
        return publicacionService.misPublicaciones();
    }

    @GetMapping("/miperfil")
    public UsuarioPerfilDTO obtenerMiPerfil() {
        return usuarioService.miPerfil();
    }

    @PutMapping("/editarperfil")
    public UsuarioPerfilDTO editarPerfil(@RequestBody UsuarioPerfilDTO dto) {
        return usuarioService.editarPerfil(dto);
    }
}
