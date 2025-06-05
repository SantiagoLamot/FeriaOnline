package com.feriaonline.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feriaonline.authentication.service.JwtService;
import com.feriaonline.dto.TransaccionRequest;
import com.feriaonline.entidades.EstadoTransaccion;
import com.feriaonline.entidades.Publicacion;
import com.feriaonline.entidades.Transaccion;
import com.feriaonline.entidades.Usuario;
import com.feriaonline.entidadesDTO.TransaccionDTO;
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

        @Autowired
        private JwtService jwtService;

        public void realizarCompra(TransaccionRequest compraDTO) {
                // Buscar entidades relacionadas a la transaccion
                Publicacion publicacion = publicacionRepository.findById(compraDTO.publicacionId())
                                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
                System.out.println("ID del comprador recibido: " + compraDTO.compradorId());
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

                System.out.println("metodo de pago: " + compraDTO.metodoDePago());
                transaccionRepository.save(transaccion);
        }

        public List<TransaccionDTO> misVentas() {
                return transaccionRepository.findByUsuarioVendedor_Id(jwtService.obtenerIdUsuarioAutenticado())
                        .stream()
                        .map(TransaccionDTO::new)
                        .collect(Collectors.toList());
        }

}
