package com.feriaonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feriaonline.entidades.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    List<Transaccion> findByVendedor_Id(int id);

    List<Transaccion> findByComprador_Id(int id);
}

