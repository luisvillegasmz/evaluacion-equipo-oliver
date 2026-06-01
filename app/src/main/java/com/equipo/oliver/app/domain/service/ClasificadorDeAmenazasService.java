package com.equipo.oliver.app.domain.service;

import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.domain.model.NivelRiesgo;

public class ClasificadorDeAmenazasService {

    // Regla de negocio: clasifica según cantidad de síntomas
    public NivelRiesgo clasificar(int cantidadSintomas) {
        if (cantidadSintomas >= 10) return NivelRiesgo.CRITICO;
        if (cantidadSintomas >= 6)  return NivelRiesgo.ALTO;
        if (cantidadSintomas >= 3)  return NivelRiesgo.MEDIO;
        return NivelRiesgo.BAJO;
    }

    // Regla de negocio: nivel CRITICO requiere mínimo 3 síntomas
    public void validarReglasCritico(Afectado afectado) {
        if (afectado.getNivelRiesgo() == NivelRiesgo.CRITICO
                && afectado.getCantidadSintomas() < 3) {
            throw new IllegalArgumentException(
                "Un afectado CRÍTICO debe tener al menos 3 síntomas registrados."
            );
        }
    }
}