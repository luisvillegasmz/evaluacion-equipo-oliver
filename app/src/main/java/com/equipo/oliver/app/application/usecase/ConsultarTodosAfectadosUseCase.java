package com.equipo.oliver.app.application.usecase;

import java.util.List;

import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.domain.port.AfectadoRepositoryPort;

public class ConsultarTodosAfectadosUseCase {

    private final AfectadoRepositoryPort repositoryPort;

    public ConsultarTodosAfectadosUseCase(AfectadoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public List<Afectado> ejecutar() {
        return repositoryPort.findAll();
    }
}