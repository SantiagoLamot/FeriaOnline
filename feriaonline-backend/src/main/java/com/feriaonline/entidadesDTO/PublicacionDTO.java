package com.feriaonline.entidadesDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.feriaonline.entidades.EstadoPublicacion;
import com.feriaonline.entidades.Publicacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<ImagenPublicacionDTO> imagenes;

    public PublicacionDTO(Publicacion p){
        this.id = p.getId();
        this.nombreProducto = p.getNombreProducto();
        this.descripcion = p.getDescripcion();
        this.precio = p.getPrecio();
        this.estado = p.getEstado();
        this.idUsuarioVendedor = p.getUsuarioVendedor().getId();
        this.imagenes.addAll(p.getImagenes().stream()
                                .map(ImagenPublicacionDTO::new)
                                .collect(Collectors.toList()));
    }

}
