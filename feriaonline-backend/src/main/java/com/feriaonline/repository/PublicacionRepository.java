package com.feriaonline.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feriaonline.entidades.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer>{
    
}