package com.example.dpocaspulgasvet.controller;

import com.example.dpocaspulgasvet.security.JwtUtils; // Asegúrate de que termine en 's'
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil; // Inyecta tu clase JwtUtils

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest)
    {
        System.out.println(">>> HASH PERFECTO GENERADO POR TU ENTREGABLE: " + new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("Victor2704xxx"));
        try {
            String usuario = loginRequest.get("usuario");
            String contrasena = loginRequest.get("contrasena");

            // 1. Spring Security valida el texto plano vs el hash de la BD
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario, contrasena)
            );

            // 2. Si es correcto, lo guardamos en el contexto
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3. Extraemos el ROL del usuario autenticado
            // Tomamos el primer rol asignado (ej. "ROLE_ADMIN" o "ADMIN")
            String rol = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER"); // Rol por defecto si no tiene ninguno

            // 4. Enviamos el username y el rol a tu método de 2 parámetros
            String jwt = jwtUtil.generateJwtToken(authentication.getName(), rol);

            // 5. Armamos la respuesta para React
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Usuario o contraseña incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error interno: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}