package com.example.dpocaspulgasvet.service;
import com.example.dpocaspulgasvet.entity.CitaSpa;
import com.example.dpocaspulgasvet.repository.CitaSpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SpaServiceImpl implements SpaService {

    @Autowired
    private CitaSpaRepository repository;

    @Override @Transactional(readOnly = true)
    public List<CitaSpa> listarTodo() { return repository.findAll(); }

    @Override @Transactional
    public CitaSpa registrar(CitaSpa citaSpa) { return repository.save(citaSpa); }

    @Override @Transactional
    public Optional<CitaSpa> actualizar(Integer id, CitaSpa updated) {
        return repository.findById(id).map(s -> {
            s.setFechaSpa(updated.getFechaSpa());
            s.setHoraSpa(updated.getHoraSpa());
            s.setEstado(updated.getEstado());
            s.setPrecioSpa(updated.getPrecioSpa());
            return repository.save(s);
        });
    }

    @Override @Transactional
    public void eliminar(Integer id) { repository.deleteById(id); }
}