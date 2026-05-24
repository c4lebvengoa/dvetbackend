package com.example.dpocaspulgasvet.service;

import com.example.dpocaspulgasvet.entity.Personal;
import java.util.List;
import java.util.Optional;

public interface PersonalService {
    List<Personal> listarPersonal(String search);
    Personal guardar(Personal personal);
    Optional<Personal> actualizar(Integer id, Personal personal);
    void eliminar(Integer id);
}