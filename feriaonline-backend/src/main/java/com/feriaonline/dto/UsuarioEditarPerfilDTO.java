package com.feriaonline.dto;

import lombok.Data;

@Data
public class UsuarioEditarPerfilDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String fotoDePerfil;
}
