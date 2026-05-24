package com.example.dpocaspulgasvet.service;

import com.example.dpocaspulgasvet.entity.CitaSpa;
import java.util.List;
import java.util.Optional;

public interface SpaService {
    List<CitaSpa> listarTodo();
    CitaSpa registrar(CitaSpa citaSpa);
    Optional<CitaSpa> actualizar(Integer id, CitaSpa citaSpa);
    void eliminar(Integer id);
}