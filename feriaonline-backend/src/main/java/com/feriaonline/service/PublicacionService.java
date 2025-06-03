package com.feriaonline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.entidades.EstadoPublicacion;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.repository.PublicacionRepository;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    JwtService jwtService;

    public List<PublicacionDTO> obtenerTodasLasPublicaciones(){
        return publicacionRepository.findByEstado(EstadoPublicacion.Activo)
            .stream()
            .map(PublicacionDTO::new)
            .collect(Collectors.toList());
    }
    public List<PublicacionDTO> misPublicaciones(){
        return publicacionRepository.findByUsuarioVendedor_Id(jwtService.obtenerIdUsuarioAutenticado())
            .stream()
            .map(PublicacionDTO::new)
            .collect(Collectors.toList());
    }
}