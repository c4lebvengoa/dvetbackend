package com.example.dpocaspulgasvet.controller;

import com.example.dpocaspulgasvet.entity.Internamiento;
import com.example.dpocaspulgasvet.repository.InternamientoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/internamientos")
public class InternamientoController {

    @Autowired
    private InternamientoRepository repo;

    @GetMapping
    public List<Internamiento> getAll() { return repo.findAll(); }

    @PostMapping
    public Internamiento create(@RequestBody Internamiento i) { return repo.save(i); }

    @PutMapping("/{id}")
    public ResponseEntity<Internamiento> update(@PathVariable Integer id, @RequestBody Internamiento updated) {
        return repo.findById(id).map(i -> {
            i.setMotivo(updated.getMotivo());
            i.setSintomas(updated.getSintomas());
            i.setFechaSalida(updated.getFechaSalida());
            i.setHoraSalida(updated.getHoraSalida());
            return ResponseEntity.ok(repo.save(i));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}