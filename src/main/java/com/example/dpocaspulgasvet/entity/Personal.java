package com.example.dpocaspulgasvet.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Personal")
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_personal")
    private Integer codPersonal;

    @Column(name = "ap_paterno", nullable = false, length = 50)
    private String apPaterno;

    @Column(name = "ap_materno", nullable = false, length = 50)
    private String apMaterno;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(name = "tipo_doc", nullable = false, length = 20)
    private String tipoDoc;

    @Column(name = "nro_doc", nullable = false, length = 20)
    private String nroDoc;

    @Column(nullable = false, length = 15)
    private String celular;

    @Column(length = 100)
    private String correo;

    @Column(length = 150)
    private String direccion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal sueldo;

    @Column(name = "seguro_vida", nullable = false, columnDefinition = "TEXT")
    private String seguroVida;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String cv;

    @Column(name = "comision_venta", precision = 5, scale = 2)
    private BigDecimal comisionVenta;

    @Column(name = "bono_porDemanda", precision = 5, scale = 2)
    private BigDecimal bonoPorDemanda;

    @Column(length = 50)
    private String especialidad;

    @Column(name = "id_tipoPersonal", nullable = false)
    private Integer idTipoPersonal;

    @Column(name = "id_distrito")
    private Integer idDistrito;


}
