package com.example.dpocaspulgasvet.controller;
import com.example.dpocaspulgasvet.entity.Personal;
import com.example.dpocaspulgasvet.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public List<Personal> getAll(@RequestParam(required = false) String search) {
        return personalService.listarPersonal(search);
    }

    @PostMapping
    public ResponseEntity<Personal> create(@RequestBody Personal personal) {
        return ResponseEntity.ok(personalService.guardar(personal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> update(@PathVariable Integer id, @RequestBody Personal updated) {
        return personalService.actualizar(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        personalService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}