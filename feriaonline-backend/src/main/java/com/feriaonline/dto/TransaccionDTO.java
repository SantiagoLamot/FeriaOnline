package com.feriaonline.dto;

import java.time.LocalDateTime;

public record TransaccionRequest (
    Integer id,
    Integer publicacionId,
    Integer compradorId,
    Integer vendedorId,
    String metodoDePago,
    LocalDateTime fecha,
    String estado
){}