package com.example.dpocaspulgasvet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "movilidad")
public class Movilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movilidad")
    private Integer idMovilidad;

    @Column(name = "direccion_recojo", length = 60)
    private String direccionRecojo;

    @Column(name = "hora_servicio")
    private LocalTime horaServicio;

    @Column(name = "fecha_servicio")
    private LocalDate fechaServicio;

    @Column(name = "tipo_servicio", length = 30)
    private String tipoServicio;

    @Column(length = 200)
    private String obs;

    @Column(length = 25)
    private String estado = "Pendiente";

    @Column(name = "cod_propietario")
    private Integer codPropietario;


}
