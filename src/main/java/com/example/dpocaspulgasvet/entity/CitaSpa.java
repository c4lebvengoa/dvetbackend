package com.example.dpocaspulgasvet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "citas_spa")
public class CitaSpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spa")
    private Integer idSpa;

    @Column(name = "fecha_spa")
    private LocalDate fechaSpa;

    @Column(name = "hora_spa")
    private LocalTime horaSpa;

    @Column(name = "id_trabajador")
    private Integer idTrabajador;

    @Column(name = "id_mascota")
    private Integer idMascota;

    @Column(length = 20)
    private String tamano;

    @Column(name = "tipo_servicio", length = 20)
    private String tipoServicio;

    @Column(name = "tipo_bano", length = 30)
    private String tipoBano;

    @Column(name = "tipo_corte", length = 30)
    private String tipoCorte;

    @Column(length = 20)
    private String estado;

    @Column(name = "precio_spa", precision = 5, scale = 2)
    private BigDecimal precioSpa;


}
