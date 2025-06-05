package com.feriaonline.entidadesDTO;

import java.time.LocalDateTime;

import com.feriaonline.entidades.EstadoTransaccion;
import com.feriaonline.entidades.Transaccion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionDTO {

    private Integer id;
    private Integer idPublicacion;
    private Integer idComprador;
    private Integer idVendedor;
    private String metodoDePago;
    private LocalDateTime fecha;
    private EstadoTransaccion estado;
    
    public TransaccionDTO(Transaccion t){
    this.id = t.getId();
    this.idPublicacion = t.getPublicacion().getId();
    this.idComprador = t.getComprador().getId();
    this.idVendedor = t.getVendedor().getId();
    this.metodoDePago = t.getMetodoDePago();
    this.fecha = t.getFecha();
    this.estado = t.getEstado();
    };
}
