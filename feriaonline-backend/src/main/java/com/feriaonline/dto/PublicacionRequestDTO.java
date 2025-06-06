package com.feriaonline.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import com.feriaonline.entidades.EstadoPublicacion;

@Data
public class PublicacionRequestDTO {
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private EstadoPublicacion estado;
    private List<String> imagenesBase64; // o urls, depende de cómo las manejes
}