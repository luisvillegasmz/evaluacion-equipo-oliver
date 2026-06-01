package com.equipo.oliver.app.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Afectado {
    private Long id;
    private String nombre;
    private String ubicacion;
    private int cantidadSintomas;
    private NivelRiesgo nivelRiesgo;
    private boolean activo;
}