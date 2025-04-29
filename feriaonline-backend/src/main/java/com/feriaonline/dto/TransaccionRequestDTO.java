package com.feriaonline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionRequestDTO {

    @NotNull(message = "El ID de la publicación es obligatorio")
    private Integer publicacionId;

    @NotNull(message = "El ID del comprador es obligatorio")
    private Integer compradorId;

    @NotNull(message = "El ID del vendedor es obligatorio")
    private Integer vendedorId;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 50, message = "El método de pago no puede superar 50 caracteres")
    private String metodoDePago;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
        regexp = "Pendiente|Completado|Cancelado",
        message = "El estado debe ser Pendiente, Completado o Cancelado"
    )
    private String estado;
}