package com.example.dpocaspulgasvet.service;

import com.example.dpocaspulgasvet.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios(String search);
    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> actualizarUsuario(Integer id, Usuario usuario);
    void eliminarUsuario(Integer id);
}