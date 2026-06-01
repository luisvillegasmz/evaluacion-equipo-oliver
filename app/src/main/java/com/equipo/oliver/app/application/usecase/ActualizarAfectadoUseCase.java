package com.equipo.oliver.app.application.usecase;

import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.domain.model.NivelRiesgo;
import com.equipo.oliver.app.domain.port.AfectadoRepositoryPort;
import com.equipo.oliver.app.domain.service.ClasificadorDeAmenazasService;

public class ActualizarAfectadoUseCase {

    private final AfectadoRepositoryPort repositoryPort;
    private final ClasificadorDeAmenazasService clasificador;

    public ActualizarAfectadoUseCase(AfectadoRepositoryPort repositoryPort,
                                     ClasificadorDeAmenazasService clasificador) {
        this.repositoryPort = repositoryPort;
        this.clasificador = clasificador;
    }

    public Afectado ejecutar(Long id, Afectado datosNuevos) {
        Afectado existente = repositoryPort.findById(id)
            .orElseThrow(() -> new RuntimeException("Afectado no encontrado con id: " + id));

        existente.setNombre(datosNuevos.getNombre());
        existente.setUbicacion(datosNuevos.getUbicacion());
        existente.setCantidadSintomas(datosNuevos.getCantidadSintomas());

        NivelRiesgo nivel = clasificador.clasificar(existente.getCantidadSintomas());
        existente.setNivelRiesgo(nivel);
        clasificador.validarReglasCritico(existente);

        return repositoryPort.save(existente);
    }
}
