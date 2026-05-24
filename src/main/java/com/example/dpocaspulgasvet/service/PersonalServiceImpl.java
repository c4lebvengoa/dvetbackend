package com.example.dpocaspulgasvet.service;
import com.example.dpocaspulgasvet.entity.Personal;
import com.example.dpocaspulgasvet.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Personal> listarPersonal(String search) {
        if (search != null && !search.isEmpty()) {
            return personalRepository.findByNombreOrDni(search);
        }
        return personalRepository.findAll();
    }

    @Override
    @Transactional
    public Personal guardar(Personal personal) {
        // Aquí puedes inyectar lógica por defecto, como validar DNI único
        return personalRepository.save(personal);
    }

    @Override
    @Transactional
    public Optional<Personal> actualizar(Integer id, Personal updated) {
        return personalRepository.findById(id).map(p -> {
            p.setApPaterno(updated.getApPaterno());
            p.setApMaterno(updated.getApMaterno());
            p.setNombres(updated.getNombres());
            p.setCelular(updated.getCelular());
            p.setCorreo(updated.getCorreo());
            p.setDireccion(updated.getDireccion());
            p.setSueldo(updated.getSueldo());
            p.setComisionVenta(updated.getComisionVenta());
            p.setBonoPorDemanda(updated.getBonoPorDemanda());
            p.setEspecialidad(updated.getEspecialidad());
            p.setIdTipoPersonal(updated.getIdTipoPersonal());
            p.setIdDistrito(updated.getIdDistrito());
            return personalRepository.save(p);
        });
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        personalRepository.deleteById(id);
    }
}
