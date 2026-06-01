package com.equipo.oliver.app.infrastructure.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AfectadoResponseDTO {
    private Long id;
    private String nombre;
    private String ubicacion;
    private int cantidadSintomas;
    private String nivelRiesgo;
    private boolean activo;
}