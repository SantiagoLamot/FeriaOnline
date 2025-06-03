package com.feriaonline.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Transacciones_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Orden")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Publicacion", nullable = false)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario_comprador", nullable = false)
    private Usuario comprador;

    // Nuevo campo para el usuario vendedor
    @ManyToOne
    @JoinColumn(name = "id_usuario_vendedor", nullable = false)
    private Usuario vendedor;

    @Column(name = "metodo_de_pago", nullable = false, length = 50)
    private String metodoDePago;

    @Column(name = "Fecha", nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoTransaccion estado;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}