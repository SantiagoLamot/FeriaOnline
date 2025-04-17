package com.feriaonline.entidadesDTO;

import com.feriaonline.entidades.EstadoTransaccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Integer idPublicacion;

    @NotNull(message = "El ID del comprador es obligatorio")
    private Integer idComprador;

    @NotNull(message = "El ID del vendedor es obligatorio")
    private Integer idVendedor;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 50, message = "El método de pago no puede superar los 50 caracteres")
    private String metodoDePago;

    @NotNull(message = "El estado de la transacción es obligatorio")
    private EstadoTransaccion estado;
}

