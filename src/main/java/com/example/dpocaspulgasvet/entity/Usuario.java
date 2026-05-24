package com.example.dpocaspulgasvet.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_personal")
    private Integer codPersonal;

    @Column(name = "cod_propietario")
    private Integer codPropietario;

    @Column(nullable = false, length = 50)
    private String usuario;

    @Column(nullable = false, length = 50)
    private String correo;

    @Column(nullable = false, length = 255) // Ampliado para soportar hash de BCrypt
    private String contrasena;

    @Column(nullable = false, length = 25)
    private String rol;

    @Column(nullable = false)
    private Integer activo = 1;


}