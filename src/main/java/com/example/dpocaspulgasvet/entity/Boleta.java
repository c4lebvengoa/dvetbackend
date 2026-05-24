package com.example.dpocaspulgasvet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Boleta")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Integer idBoleta;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "forma_pago", nullable = false, length = 20)
    private String formaPago;

    @Column(name = "importe_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal importeTotal;

    @Column(name = "cod_propietario")
    private Integer codPropietario;

    @Column(name = "cod_personal")
    private Integer codPersonal;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_boleta") // Mapea la FK en DetalleBoleta automáticamente
    private List<DetalleBoleta> detalles;


}