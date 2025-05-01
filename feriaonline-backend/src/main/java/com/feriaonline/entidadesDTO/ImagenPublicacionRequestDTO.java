package com.feriaonline.entidadesDTO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenPublicacionRequestDTO {

    @NotNull(message = "La imagen es obligatoria")
    private MultipartFile imagen;

    @NotNull(message = "El ID de la publicación es obligatorio")
    private Integer publicacionId;
}
