package com.feriaonline.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagenes_por_publicacion_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenPublicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagenes")
    private Integer id;

    @Column(name = "imagen", nullable = false, columnDefinition = "TEXT")
    private String url;

    //El fetch = FetchType.LAZY evita traer la publicación completa cuando solo 
    //necesitás la imagen, lo que mejora el rendimiento en algunos casos.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", nullable = false)
    private Publicacion publicacion;

}
