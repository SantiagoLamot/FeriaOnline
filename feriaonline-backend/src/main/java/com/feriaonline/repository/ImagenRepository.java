package com.feriaonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feriaonline.entidades.ImagenPublicacion;

public interface ImagenRepository extends JpaRepository<ImagenPublicacion, Integer> {
    List<ImagenPublicacion> findByPublicacion_Id(int publicacionId);
}