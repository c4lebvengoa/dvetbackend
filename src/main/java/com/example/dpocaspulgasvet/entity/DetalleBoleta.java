package com.example.dpocaspulgasvet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "DetalleBoleta")
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalleBoleta")
    private Integer idDetalleBoleta;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "id_citamed")
    private Integer idCitamed;

    @Column(name = "id_spa")
    private Integer idSpa;

    @Column(name = "id_procedimiento")
    private Integer idProcedimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;


}