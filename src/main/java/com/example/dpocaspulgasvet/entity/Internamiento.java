package com.example.dpocaspulgasvet.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "internamientos")
public class Internamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_internamiento")
    private Integer idInternamiento;

    @Column(name = "id_historia")
    private Integer idHistoria;

    @Column(length = 50)
    private String motivo;

    @Column(length = 50)
    private String sintomas;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "hora_ingreso")
    private LocalTime horaIngreso;


}