package com.feriaonline.entidadesDTO;

import com.feriaonline.entidades.EstadoTransaccion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
