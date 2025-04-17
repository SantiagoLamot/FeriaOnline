package com.feriaonline.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Publicaciones_tb")
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Publicacion")
    private Integer id;

    @Column(name = "NombreProducto", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "Descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "Precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoPublicacion estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UsuarioVendedor", nullable = false)
    private Usuario usuarioVendedor; 
}
