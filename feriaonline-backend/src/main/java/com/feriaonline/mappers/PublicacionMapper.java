package com.feriaonline.mappers;

import com.feriaonline.entidades.Publicacion;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.entidadesDTO.PublicacionDTO;
import com.feriaonline.entidadesDTO.PublicacionRequestDTO;
import org.mapstruct.*;

/* Mapeos necesarios
Publicacion → PublicacionDTO
PublicacionRequestDTO → Publicacion
PublicacionDTO → Publicacion (si lo necesitás)
Ignorar el mapeo directo de Usuario completo: en DTO solo usamos el idUsuarioVendedor. */

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface PublicacionMapper {

    // Publicacion -> PublicacionDTO
    @Mapping(source = "usuarioVendedor.id", target = "idUsuarioVendedor")
    PublicacionDTO publicacionToPublicacionDTO(Publicacion publicacion);

    // PublicacionRequestDTO -> Publicacion
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idUsuarioVendedor", target = "usuarioVendedor", qualifiedByName = "mapUsuarioFromId")
    Publicacion publicacionRequestDTOToPublicacion(PublicacionRequestDTO dto);

    // PublicacionDTO -> Publicacion (si lo necesitás)
    @Mapping(source = "idUsuarioVendedor", target = "usuarioVendedor", qualifiedByName = "mapUsuarioFromId")
    Publicacion publicacionDTOToPublicacion(PublicacionDTO dto);

    // Método auxiliar para mapear un ID de usuario a una instancia "dummy"
    @Named("mapUsuarioFromId")
    default Usuario mapUsuarioFromId(Integer id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

    // Actualización parcial de una entidad Publicacion existente desde un RequestDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idUsuarioVendedor", target = "usuarioVendedor", qualifiedByName = "mapUsuarioFromId")
    void updatePublicacionFromDTO(PublicacionRequestDTO dto, @MappingTarget Publicacion publicacion);
}

/*Notas importantes
El método mapUsuarioFromId te permite transformar solo el id en un objeto Usuario para asignarlo como
usuarioVendedor sin necesidad de consultar la base de datos.
Si más adelante necesitás hacer una validación real del usuario desde base de datos, 
ese id se puede procesar en el servicio, no en el mapper. */
