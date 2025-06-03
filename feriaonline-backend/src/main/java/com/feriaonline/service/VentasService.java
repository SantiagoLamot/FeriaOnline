package com.feriaonline.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feriaonline.dto.TransaccionRequest;
import com.feriaonline.entidades.EstadoTransaccion;
import com.feriaonline.entidades.Publicacion;
import com.feriaonline.entidades.Transaccion;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.repository.PublicacionRepository;
import com.feriaonline.repository.TransaccionRepository;
import com.feriaonline.repository.UsuarioRepository;

@Service
public class VentasService {

        @Autowired
        private TransaccionRepository transaccionRepository;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private PublicacionRepository publicacionRepository;

        public void realizarCompra(TransaccionRequest compraDTO) {
                // Buscar entidades relacionadas a la transaccion
                Publicacion publicacion = publicacionRepository.findById(compraDTO.publicacionId())
                                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

                Usuario comprador = usuarioRepository.findById(compraDTO.compradorId())
                                .orElseThrow(() -> new RuntimeException("Comprador no encontrado"));

                Usuario vendedor = usuarioRepository.findById(compraDTO.vendedorId())
                                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

                if (compraDTO.estado() == null) {
                        throw new IllegalArgumentException("El estado no puede ser null");
                }

                EstadoTransaccion estado;
                try {
                        estado = EstadoTransaccion.valueOf(compraDTO.estado());
                } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Estado de transacción inválido: " + compraDTO.estado());
                }

                Transaccion transaccion = Transaccion.builder()
                                .publicacion(publicacion)
                                .comprador(comprador)
                                .vendedor(vendedor)
                                .metodoDePago(compraDTO.metodoDePago())
                                .estado(estado)
                                .fecha(compraDTO.fecha() != null ? compraDTO.fecha() : LocalDateTime.now())
                                .build();

                System.out.println("Comprador: " + comprador);
                System.out.println("Vendedor: " + vendedor);
                System.out.println("Publicación: " + publicacion);
                System.out.println("Estado de la transacción: " + estado);
                System.out.println("Fecha de la transacción: " + transaccion.getFecha());

                transaccionRepository.save(transaccion);
        }

}
