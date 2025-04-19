package com.feriaonline.mappers;

import com.feriaonline.entidades.Transaccion;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.entidades.Publicacion;
import com.feriaonline.entidadesDTO.TransaccionDTO;
import com.feriaonline.entidadesDTO.TransaccionRequestDTO;
import org.mapstruct.*;

/*Transaccion → TransaccionDTO
TransaccionRequestDTO → Transaccion
Posiblemente TransaccionDTO → Transaccion (por si en algún momento querés manipular transacciones desde DTOs directamente).
Ignorar fecha al mapear desde DTO o RequestDTO, ya que se genera automáticamente con @PrePersist. */

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    // Transaccion -> TransaccionDTO
    @Mapping(source = "publicacion.id", target = "idPublicacion")
    @Mapping(source = "comprador.id", target = "idComprador")
    @Mapping(source = "vendedor.id", target = "idVendedor")
    TransaccionDTO transaccionToTransaccionDTO(Transaccion transaccion);

    // TransaccionRequestDTO -> Transaccion (ignora id y fecha)
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fecha", ignore = true),
        @Mapping(source = "idPublicacion", target = "publicacion", qualifiedByName = "mapPublicacionFromId"),
        @Mapping(source = "idComprador", target = "comprador", qualifiedByName = "mapUsuarioFromId"),
        @Mapping(source = "idVendedor", target = "vendedor", qualifiedByName = "mapUsuarioFromId")
    })
    Transaccion transaccionRequestDTOToTransaccion(TransaccionRequestDTO dto);

    // DTO → Entidad (si llegás a usarlo en el futuro)
    @Mappings({
        @Mapping(source = "idPublicacion", target = "publicacion", qualifiedByName = "mapPublicacionFromId"),
        @Mapping(source = "idComprador", target = "comprador", qualifiedByName = "mapUsuarioFromId"),
        @Mapping(source = "idVendedor", target = "vendedor", qualifiedByName = "mapUsuarioFromId")
    })
    Transaccion transaccionDTOToTransaccion(TransaccionDTO dto);

    // Métodos auxiliares para mapear por ID
    @Named("mapUsuarioFromId")
    default Usuario mapUsuarioFromId(Integer id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

    @Named("mapPublicacionFromId")
    default Publicacion mapPublicacionFromId(Integer id) {
        if (id == null) return null;
        Publicacion pub = new Publicacion();
        pub.setId(id);
        return pub;
    }

    // Actualización parcial desde el request (por si necesitás updates)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fecha", ignore = true),
        @Mapping(source = "idPublicacion", target = "publicacion", qualifiedByName = "mapPublicacionFromId"),
        @Mapping(source = "idComprador", target = "comprador", qualifiedByName = "mapUsuarioFromId"),
        @Mapping(source = "idVendedor", target = "vendedor", qualifiedByName = "mapUsuarioFromId")
    })
    void updateTransaccionFromDTO(TransaccionRequestDTO dto, @MappingTarget Transaccion transaccion);
}

/*Detalles clave:
fecha se ignora en los mapeos hacia entidad, ya que se setea automáticamente con @PrePersist.
Si en algún momento necesitás mostrar la fecha en el DTO, simplemente agregala al TransaccionDTO.
El mapper está preparado para funcionar con Spring (componentModel = "spring") y con métodos auxiliares
simples que evitan necesidad de consultar la BD directamente desde el mapper. */
