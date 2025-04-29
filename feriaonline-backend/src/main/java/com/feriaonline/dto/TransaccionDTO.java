package com.feriaonline.dto;

import java.time.LocalDateTime;

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
    private Integer publicacionId;
    private Integer compradorId;
    private Integer vendedorId;
    private String metodoDePago;
    private LocalDateTime fecha;
    private String estado;
}