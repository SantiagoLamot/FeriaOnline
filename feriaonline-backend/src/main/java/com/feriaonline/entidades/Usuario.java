package com.feriaonline.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuarios_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Integer id;

    @Column(name = "NombreDeUsuario", nullable = false, unique = true, length = 50)
    private String nombreDeUsuario;

    @Column(name = "Correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "Contrasena", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "Nombre", length = 50)
    private String nombre;

    @Column(name = "Apellido", length = 50)
    private String apellido;

    @Column(name = "FotoDePerfil", columnDefinition = "TEXT")
    private String fotoDePerfil;
}
