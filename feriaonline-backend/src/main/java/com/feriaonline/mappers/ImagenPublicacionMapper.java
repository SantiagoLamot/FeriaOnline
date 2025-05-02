package com.feriaonline.mappers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.feriaonline.entidades.ImagenPublicacion;
import com.feriaonline.entidadesDTO.ImagenPublicacionDTO;


/*como MapStruct no sabe convertir automáticamente BufferedImage a String (Base64) 
ni viceversa, vamos a usar un @Mapper con un @Mapping y métodos auxiliares para manejar esas conversiones. */

@Mapper(componentModel = "spring")
public interface ImagenPublicacionMapper {

    ImagenPublicacionMapper INSTANCE = Mappers.getMapper(ImagenPublicacionMapper.class);

    /*

     @Mappings({
        @Mapping(source = "imagen", target = "imagenBase64", qualifiedByName = "bufferedImageToBase64"),
        @Mapping(source = "publicacion.id", target = "publicacionId")
    })
    ImagenPublicacionDTO toDTO(ImagenPublicacion entity);
    @Mappings({
        @Mapping(source = "imagenBase64", target = "imagen", qualifiedByName = "base64ToBufferedImage"),
        @Mapping(target = "publicacion", ignore = true) // se debe setear aparte
    })
    */
    ImagenPublicacion toEntity(ImagenPublicacionDTO dto);

    @Named("bufferedImageToBase64")
    default String bufferedImageToBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir BufferedImage a Base64", e);
        }
    }

    @Named("base64ToBufferedImage")
    default BufferedImage base64ToBufferedImage(String base64) {
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir Base64 a BufferedImage", e);
        }
    }
}

/*Importante:
El campo publicacion se ignora al hacer toEntity porque MapStruct no sabe cómo convertir
 un Integer a una entidad Publicacion. Podés inyectarla después manualmente en el servicio.

Usamos @Named y qualifiedByName para que MapStruct sepa usar tus métodos custom. */
