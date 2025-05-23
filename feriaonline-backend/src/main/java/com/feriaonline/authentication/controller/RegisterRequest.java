package com.feriaonline.authentication.controller;

public record RegisterRequest(
    String nombreDeUsuario,
    String correo,
    String contrasena,
    String nombre,
    String apellido
) {}
