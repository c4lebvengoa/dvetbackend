package com.example.dpocaspulgasvet.service;

import com.example.dpocaspulgasvet.entity.Boleta;
import java.util.List;

public interface VentaService {
    List<Boleta> listarTodas();
    Boleta registrarVenta(Boleta boleta);
    void eliminarVenta(Integer id);
}