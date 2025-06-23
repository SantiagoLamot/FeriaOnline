package com.feriaonline.dto;

import java.math.BigDecimal;

import com.feriaonline.entidades.EstadoPublicacion;

import lombok.Data;

@Data
public class PublicacionRequestDTO {
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private EstadoPublicacion estado;
}