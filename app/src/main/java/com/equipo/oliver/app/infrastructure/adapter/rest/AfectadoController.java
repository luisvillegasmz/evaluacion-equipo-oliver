package com.equipo.oliver.app.infrastructure.adapter.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo.oliver.app.application.usecase.ActualizarAfectadoUseCase;
import com.equipo.oliver.app.application.usecase.ConsultarAfectadoUseCase;
import com.equipo.oliver.app.application.usecase.ConsultarTodosAfectadosUseCase;
import com.equipo.oliver.app.application.usecase.RegistrarAfectadoUseCase;
import com.equipo.oliver.app.domain.model.Afectado;
import com.equipo.oliver.app.infrastructure.adapter.rest.dto.AfectadoRequestDTO;
import com.equipo.oliver.app.infrastructure.adapter.rest.dto.AfectadoResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/afectados")
@RequiredArgsConstructor
public class AfectadoController {

    private final RegistrarAfectadoUseCase registrarUseCase;
    private final ConsultarAfectadoUseCase consultarUseCase;
    private final ConsultarTodosAfectadosUseCase consultarTodosUseCase;
    private final ActualizarAfectadoUseCase actualizarUseCase;

    @PostMapping
    public ResponseEntity<AfectadoResponseDTO> registrar(@RequestBody AfectadoRequestDTO request) {
        Afectado afectado = toDomain(request);
        Afectado guardado = registrarUseCase.ejecutar(afectado);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(guardado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfectadoResponseDTO> consultar(@PathVariable Long id) {
        return consultarUseCase.ejecutar(id)
                .map(a -> ResponseEntity.ok(toResponse(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AfectadoResponseDTO>> consultarTodos() {
        List<AfectadoResponseDTO> lista = consultarTodosUseCase.ejecutar()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AfectadoResponseDTO> actualizar(@PathVariable Long id,
                                                          @RequestBody AfectadoRequestDTO request) {
        Afectado actualizado = actualizarUseCase.ejecutar(id, toDomain(request));
        return ResponseEntity.ok(toResponse(actualizado));
    }

    // Mapeo Request → Domain
    private Afectado toDomain(AfectadoRequestDTO dto) {
        return Afectado.builder()
                .nombre(dto.getNombre())
                .ubicacion(dto.getUbicacion())
                .cantidadSintomas(dto.getCantidadSintomas())
                .build();
    }

    // Mapeo Domain → Response
    private AfectadoResponseDTO toResponse(Afectado afectado) {
        return AfectadoResponseDTO.builder()
                .id(afectado.getId())
                .nombre(afectado.getNombre())
                .ubicacion(afectado.getUbicacion())
                .cantidadSintomas(afectado.getCantidadSintomas())
                .nivelRiesgo(afectado.getNivelRiesgo().name())
                .activo(afectado.isActivo())
                .build();
    }
}