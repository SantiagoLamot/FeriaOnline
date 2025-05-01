package com.feriaonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feriaonline.repository.PublicacionRepository;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;

    
}