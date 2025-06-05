package com.feriaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feriaonline.dto.TransaccionRequest;
import com.feriaonline.service.VentasService;

@RestController
@RequestMapping("/ventas")
public class VentasController {
    
    @Autowired
    private VentasService ventasService;

    @PostMapping("/compra")
    public ResponseEntity<String> realizarCompra(@RequestBody TransaccionRequest compraDTO) {
        try {
            ventasService.realizarCompra(compraDTO);
            return ResponseEntity.ok("Compra realizada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar la compra: " + e.getMessage());
        }
    }
    
}