package com.feriaonline.mappers;

import com.feriaonline.entidades.Usuario;
import com.feriaonline.entidadesDTO.UsuarioDTO;
import com.feriaonline.entidadesDTO.UsuarioRequestDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Usuario -> UsuarioDTO
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    // UsuarioDTO -> Usuario (ignorando contraseña, ya que DTO no la tiene)
    @Mapping(target = "contrasena", ignore = true)
    Usuario usuarioDTOToUsuario(UsuarioDTO dto);

    // Usuario -> UsuarioRequestDTO
    UsuarioRequestDTO usuarioToUsuarioRequestDTO(Usuario usuario);

    // UsuarioRequestDTO -> Usuario (ignorando el ID)
    @Mappings({
        @Mapping(target = "id", ignore = true)
    })
    Usuario usuarioRequestDTOToUsuario(UsuarioRequestDTO requestDTO);

    // Para actualización parcial (merge de datos del request en una entidad existente)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
        @Mapping(target = "id", ignore = true)
    })
    void updateUsuarioFromDTO(UsuarioRequestDTO dto, @MappingTarget Usuario usuario);
}
