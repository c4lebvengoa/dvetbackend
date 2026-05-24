package com.example.dpocaspulgasvet.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    // Clave secreta de firma (Debe ser de al menos 256 bits)
    private final String JWT_SECRET = "TuClaveSecretaSuperSeguraParaDPocasPulgasVeterinaria2026";
    private final int JWT_EXPIRATION_MS = 86400000; // 24 horas

    // CORRECTO: Ahora JJWT prefiere trabajar directamente con la interfaz SecretKey
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    // Generar el token a partir del username
    public String generateJwtToken(String username, String role) {
        return Jwts.builder()
                .subject(username) // CORRECTO: .setSubject() pasó a ser simplemente .subject()
                .claim("role", role)
                .issuedAt(new Date()) // CORRECTO: .setIssuedAt() pasó a ser .issuedAt()
                .expiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MS)) // CORRECTO: .setExpiration() pasó a ser .expiration()
                .signWith(getSigningKey()) // CORRECTO: Ya no requiere pasar el algoritmo explícitamente, JJWT lo detecta según el tamaño de la llave
                .compact();
    }

    // Obtener el usuario del token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()             // CORRECTO: Se usa .parser() directamente en lugar de .parserBuilder()
                .verifyWith(getSigningKey()) // CORRECTO: .setSigningKey() fue reemplazado por .verifyWith()
                .build()
                .parseSignedClaims(token) // CORRECTO: .parseClaimsJws() fue reemplazado por .parseSignedClaims()
                .getPayload()             // CORRECTO: .getBody() fue reemplazado por .getPayload()
                .getSubject();
    }

    // Validar la integridad del token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Manejar excepciones de token expirado, mal formado, etc.
            System.out.println("Error de validación de JWT: " + e.getMessage());
        }
        return false;
    }
}