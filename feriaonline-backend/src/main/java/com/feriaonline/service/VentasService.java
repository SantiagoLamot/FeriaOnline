package com.feriaonline.service;

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
        //Buscar entidades relacionadas a la transaccion
        Publicacion publicacion = publicacionRepository.findById(compraDTO.publicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        Usuario comprador = usuarioRepository.findById(compraDTO.compradorId())
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado"));

        Usuario vendedor = usuarioRepository.findById(compraDTO.vendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        EstadoTransaccion estado = EstadoTransaccion.valueOf(compraDTO.estado());

        Transaccion transaccion = Transaccion.builder() // HAY QUE AGREGAR MONTO
                .publicacion(publicacion)
                .comprador(comprador)
                .vendedor(vendedor)
                .metodoDePago(compraDTO.metodoDePago())
                .estado(estado) 
                .build();

        transaccionRepository.save(transaccion);
    }
    
}
