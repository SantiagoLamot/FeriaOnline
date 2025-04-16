package com.feriaonline.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ImagenesxPublicacion_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenPublicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Imagenes")
    private Integer id;

    @Column(name = "Imagen", nullable = false, columnDefinition = "TEXT")
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", nullable = false)
    private Publicaciones publicacion;

}
