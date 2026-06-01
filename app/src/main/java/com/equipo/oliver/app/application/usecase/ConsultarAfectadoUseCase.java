package com.equipo.oliver.app.application.usecase;

import java.util.Optional;

import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.domain.port.AfectadoRepositoryPort;

public class ConsultarAfectadoUseCase {

    private final AfectadoRepositoryPort repositoryPort;

    public ConsultarAfectadoUseCase(AfectadoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Optional<Afectado> ejecutar(Long id) {
        return repositoryPort.findById(id);
    }
}
