package com.feriaonline.entidadesDTO;

import com.feriaonline.entidades.ImagenPublicacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Como buena práctica, los DTOs no deben incluir entidades completas como Publicacion, 
sino solo su ID u otra información relevante para evitar el acoplamiento innecesario.

También es importante considerar que BufferedImage no se suele utilizar directamente 
en DTOs debido a su naturaleza binaria y pesada. En su lugar, suele usarse una codificación 
como Base64 si se quiere transmitir la imagen como texto, o simplemente usar un enlace si está almacenada 
en algún sistema de archivos o servicio externo. */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenPublicacionDTO {

    private Integer id;
    private String url; // URL de la imagen
    private Integer publicacionId;

    ImagenPublicacionDTO(ImagenPublicacion i)
    {
        this.id = i.getId();
        this.publicacionId = i.getPublicacion().getId();
        this.url = i.getUrl();
    }
}

/*¿Qué deberías hacer en el servicio?
Convertir el BufferedImage a Base64 para llenar imagenBase64.

Extraer solo el ID de la publicación en lugar de pasar el objeto entero. */
