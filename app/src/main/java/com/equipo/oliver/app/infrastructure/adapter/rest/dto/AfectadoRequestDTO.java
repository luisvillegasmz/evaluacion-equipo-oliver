package com.equipo.oliver.app.infrastructure.adapter.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AfectadoRequestDTO {
    private String nombre;
    private String ubicacion;
    private int cantidadSintomas;
}