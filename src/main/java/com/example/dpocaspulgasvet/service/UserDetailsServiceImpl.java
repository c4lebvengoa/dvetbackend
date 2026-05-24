package com.example.dpocaspulgasvet.service;

import com.example.dpocaspulgasvet.entity.Usuario;
import com.example.dpocaspulgasvet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos el usuario en la BD usando tu campo 'usuario'
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en el sistema: " + username));

        // 2. Formateamos el rol para las autorizaciones de Spring Security
        String nombreRol = usuario.getRol();
        if (nombreRol != null && !nombreRol.startsWith("ROLE_")) {
            nombreRol = "ROLE_" + nombreRol;
        } else if (nombreRol == null) {
            nombreRol = "ROLE_USER"; // Rol de respaldo si estuviera nulo
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(nombreRol);

        // 3. Convertimos tu Integer 'activo' (1 o 0) a un boolean (true o false)
        boolean isEnabled = (usuario.getActivo() != null && usuario.getActivo() == 1);

        // 4. Retornamos el User oficial mapeando exactamente tus columnas
        return new User(
                usuario.getUsuario(),    // Campo string 'usuario'
                usuario.getContrasena(), // El hash guardado en 'contrasena'
                isEnabled,               // ¿Está habilitado? (true si es 1)
                true, true, true,        // Parámetros por defecto de la cuenta
                Collections.singletonList(authority)
        );
    }
}