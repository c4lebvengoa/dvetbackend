package com.example.dpocaspulgasvet.service;


import com.example.dpocaspulgasvet.entity.Usuario;
import com.example.dpocaspulgasvet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios(String search) {
        if (search != null && !search.isEmpty()) {
            return usuarioRepository.findByUsuarioContainingOrCorreoContaining(search, search);
        }
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        // Lógica de negocio: Encriptación obligatoria en capa de servicio
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> actualizarUsuario(Integer id, Usuario updated) {
        return usuarioRepository.findById(id).map(u -> {
            u.setUsuario(updated.getUsuario());
            u.setCorreo(updated.getCorreo());
            u.setRol(updated.getRol());
            u.setActivo(updated.getActivo());
            // Si el frontend envía una nueva contraseña, se vuelve a encriptar
            if (updated.getContrasena() != null && !updated.getContrasena().isEmpty()) {
                u.setContrasena(passwordEncoder.encode(updated.getContrasena()));
            }
            return usuarioRepository.save(u);
        });
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}