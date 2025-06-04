package com.feriaonline.dto;

import lombok.Data;

@Data
public class UsuarioPerfilDTO {
    private Integer id;
    private String nombreDeUsuario;
    private String correo;
    private String nombre;
    private String apellido;
    private String fotoDePerfil;
}