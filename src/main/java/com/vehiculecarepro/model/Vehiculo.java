package com.vehiculecarepro.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Table(name = "vehiculos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @Column(nullable = false, length = 60)
    private String marca;

    @Column(nullable = false, length = 60)
    private String modelo;

    @Column(nullable = false)
    private Integer anio;

    private String color;
    private String vin;
    private String tipo;     // sedan, pickup, suv...
    private String estado;   // ACTIVO, TALLER, INACTIVO...

    private LocalDate fechaRegistro;
}
