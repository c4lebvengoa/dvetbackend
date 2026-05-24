package com.example.dpocaspulgasvet.controller;

import com.example.dpocaspulgasvet.entity.Boleta;
import com.example.dpocaspulgasvet.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class BoletaController {

    @Autowired
    private VentaService ventaService; // Inyectamos el servicio, NO el repositorio

    @GetMapping
    public List<Boleta> getAll() {
        return ventaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Boleta> registrarVenta(@RequestBody Boleta boleta) {
        try {
            Boleta nuevaBoleta = ventaService.registrarVenta(boleta);
            return ResponseEntity.ok(nuevaBoleta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBoleta(@PathVariable Integer id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.ok().build();
    }
}