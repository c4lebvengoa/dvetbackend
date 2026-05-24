package com.example.dpocaspulgasvet.repository;

import com.example.dpocaspulgasvet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuario(String usuario);
    List<Usuario> findByUsuarioContainingOrCorreoContaining(String user, String email);
}