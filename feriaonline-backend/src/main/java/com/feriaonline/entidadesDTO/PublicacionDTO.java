package com.feriaonline.entidadesDTO;

import com.feriaonline.entidades.EstadoPublicacion;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicacionDTO {
    private Integer id;
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private EstadoPublicacion estado;
    private Integer idUsuarioVendedor;
}
