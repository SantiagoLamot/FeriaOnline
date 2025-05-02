package com.feriaonline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.repository.PublicacionRepository;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;

    public List<PublicacionDTO> obtenerTodasLasPublicaciones(){
        return publicacionRepository.findAll().stream().map(PublicacionDTO::new).collect(Collectors.toList());
    }
}