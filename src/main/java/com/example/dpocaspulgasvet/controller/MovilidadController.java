package com.example.dpocaspulgasvet.controller;
import com.example.dpocaspulgasvet.entity.Movilidad;
import com.example.dpocaspulgasvet.repository.MovilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/movilidad")
public class MovilidadController {

    @Autowired
    private MovilidadRepository movilidadRepository;

    @GetMapping
    public List<Movilidad> getAll() { return movilidadRepository.findAll(); }

    @PostMapping
    public Movilidad create(@RequestBody Movilidad m) { return movilidadRepository.save(m); }

    @PutMapping("/{id}")
    public ResponseEntity<Movilidad> update(@PathVariable Integer id, @RequestBody Movilidad updated) {
        return movilidadRepository.findById(id).map(m -> {
            m.setDireccionRecojo(updated.getDireccionRecojo());
            m.setFechaServicio(updated.getFechaServicio());
            m.setHoraServicio(updated.getHoraServicio());
            m.setEstado(updated.getEstado());
            m.setObs(updated.getObs());
            return ResponseEntity.ok(movilidadRepository.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        movilidadRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}