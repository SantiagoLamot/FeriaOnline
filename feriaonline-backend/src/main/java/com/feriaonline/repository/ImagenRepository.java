package com.feriaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feriaonline.entidades.ImagenPublicacion;

public interface ImagenRepository extends JpaRepository<ImagenPublicacion, Integer> {
    
}