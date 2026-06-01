package com.equipo.oliver.app.infrastructure.adapter.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "afectados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AfectadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;
    private int cantidadSintomas;
    private String nivelRiesgo;
    private boolean activo;
}