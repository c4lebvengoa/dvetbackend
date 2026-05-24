package com.example.dpocaspulgasvet.controller;

import com.example.dpocaspulgasvet.entity.CitaSpa;
import com.example.dpocaspulgasvet.service.SpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spa")
public class CitaSpaController {

    @Autowired
    private SpaService spaService;

    @GetMapping
    public List<CitaSpa> getAll() {
        return spaService.listarTodo();
    }

    @PostMapping
    public ResponseEntity<CitaSpa> create(@RequestBody CitaSpa citaSpa) {
        CitaSpa nuevaCita = spaService.registrar(citaSpa);
        return ResponseEntity.ok(nuevaCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaSpa> update(@PathVariable Integer id, @RequestBody CitaSpa updated) {
        return spaService.actualizar(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        spaService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}