package com.feriaonline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombreDeUsuario;
    private String correo;
    private String nombre;
    private String apellido;
    private String fotoDePerfil;
}
