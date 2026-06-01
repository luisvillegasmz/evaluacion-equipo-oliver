package com.equipo.oliver.app.application.usecase;

import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.domain.model.NivelRiesgo;
import com.equipo.oliver.app.domain.port.AfectadoRepositoryPort;
import com.equipo.oliver.app.domain.service.ClasificadorDeAmenazasService;

public class RegistrarAfectadoUseCase {

    private final AfectadoRepositoryPort repositoryPort;
    private final ClasificadorDeAmenazasService clasificador;

    public RegistrarAfectadoUseCase(AfectadoRepositoryPort repositoryPort,
                                    ClasificadorDeAmenazasService clasificador) {
        this.repositoryPort = repositoryPort;
        this.clasificador = clasificador;
    }

    public Afectado ejecutar(Afectado afectado) {
        NivelRiesgo nivel = clasificador.clasificar(afectado.getCantidadSintomas());
        afectado.setNivelRiesgo(nivel);
        afectado.setActivo(true);
        clasificador.validarReglasCritico(afectado);
        return repositoryPort.save(afectado);
    }
}