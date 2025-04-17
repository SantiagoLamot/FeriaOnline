package com.feriaonline.entidadesDTO;

import com.feriaonline.entidades.EstadoPublicacion;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionRequestDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre del producto no puede superar los 100 caracteres")
    private String nombreProducto;

    @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.00", inclusive = false, message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener hasta 10 dígitos enteros y 2 decimales")
    private BigDecimal precio;

    @NotNull(message = "El estado es obligatorio")
    private EstadoPublicacion estado;

    @NotNull(message = "El ID del usuario vendedor es obligatorio")
    private Integer idUsuarioVendedor;
    
}
