package com.feriaonline.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.awt.image.BufferedImage;

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
    private BufferedImage imagen;

    //El fetch = FetchType.LAZY evita traer la publicación completa cuando solo 
    //necesitás la imagen, lo que mejora el rendimiento en algunos casos.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", nullable = false)
    private Publicacion publicacion;

}
