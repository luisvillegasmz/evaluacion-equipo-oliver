package com.equipo.oliver.app.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.domain.model.NivelRiesgo;
import com.equipo.oliver.app.domain.port.AfectadoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AfectadoRepositoryAdapter implements AfectadoRepositoryPort {

    private final AfectadoJpaRepository jpaRepository;

    @Override
    public Afectado save(Afectado afectado) {
        AfectadoEntity entity = toEntity(afectado);
        AfectadoEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Afectado> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Afectado> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // Mapeo Entity → Domain
    private Afectado toDomain(AfectadoEntity entity) {
        return Afectado.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .ubicacion(entity.getUbicacion())
                .cantidadSintomas(entity.getCantidadSintomas())
                .nivelRiesgo(NivelRiesgo.valueOf(entity.getNivelRiesgo()))
                .activo(entity.isActivo())
                .build();
    }

    // Mapeo Domain → Entity
    private AfectadoEntity toEntity(Afectado afectado) {
        return AfectadoEntity.builder()
                .id(afectado.getId())
                .nombre(afectado.getNombre())
                .ubicacion(afectado.getUbicacion())
                .cantidadSintomas(afectado.getCantidadSintomas())
                .nivelRiesgo(afectado.getNivelRiesgo().name())
                .activo(afectado.isActivo())
                .build();
    }
}