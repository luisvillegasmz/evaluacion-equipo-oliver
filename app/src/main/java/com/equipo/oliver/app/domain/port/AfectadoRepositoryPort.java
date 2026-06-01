package com.equipo.oliver.app.domain.port;

import com.equipo.oliver.app.domain.model.Afectado;

import java.util.List;
import java.util.Optional;

public interface AfectadoRepositoryPort {
    Afectado save(Afectado afectado);
    Optional<Afectado> findById(Long id);
    List<Afectado> findAll();
}