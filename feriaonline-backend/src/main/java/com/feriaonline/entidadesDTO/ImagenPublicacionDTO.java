package com.feriaonline.entidadesDTO;

import lombok.*;

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
    private String imagenBase64;
    private Integer publicacionId;

}

/*¿Qué deberías hacer en el servicio?
Convertir el BufferedImage a Base64 para llenar imagenBase64.

Extraer solo el ID de la publicación en lugar de pasar el objeto entero. */
