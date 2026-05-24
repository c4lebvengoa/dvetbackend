package com.example.dpocaspulgasvet.service;
import com.example.dpocaspulgasvet.entity.Boleta;
import com.example.dpocaspulgasvet.repository.BoletaRepository;
import com.example.dpocaspulgasvet.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> listarTodas() {
        return boletaRepository.findAll();
    }

    @Override
    @Transactional // Si falla la inserción de un detalle, deshace toda la operación
    public Boleta registrarVenta(Boleta boleta) {
        if (boleta.getDetalles() == null || boleta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La boleta debe contener al menos un detalle de producto o servicio.");
        }
        // Aquí podrías agregar lógica adicional: restar stock de productos, validar precios, etc.
        return boletaRepository.save(boleta);
    }

    @Override
    @Transactional
    public void eliminarVenta(Integer id) {
        boletaRepository.deleteById(id);
    }
}
