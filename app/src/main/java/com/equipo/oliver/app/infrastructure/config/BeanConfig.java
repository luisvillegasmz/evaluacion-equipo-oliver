package com.equipo.oliver.app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.equipo.oliver.app.application.usecase.ActualizarAfectadoUseCase;
import com.equipo.oliver.app.application.usecase.ConsultarAfectadoUseCase;
import com.equipo.oliver.app.application.usecase.ConsultarTodosAfectadosUseCase;
import com.equipo.oliver.app.application.usecase.RegistrarAfectadoUseCase;
import com.equipo.oliver.app.domain.port.AfectadoRepositoryPort;
import com.equipo.oliver.app.domain.service.ClasificadorDeAmenazasService;

@Configuration
public class BeanConfig {

    @Bean
    public ClasificadorDeAmenazasService clasificadorDeAmenazasService() {
        return new ClasificadorDeAmenazasService();
    }

    @Bean
    public RegistrarAfectadoUseCase registrarAfectadoUseCase(AfectadoRepositoryPort port,
                                                             ClasificadorDeAmenazasService clasificador) {
        return new RegistrarAfectadoUseCase(port, clasificador);
    }

    @Bean
    public ConsultarAfectadoUseCase consultarAfectadoUseCase(AfectadoRepositoryPort port) {
        return new ConsultarAfectadoUseCase(port);
    }

    @Bean
    public ConsultarTodosAfectadosUseCase consultarTodosAfectadosUseCase(AfectadoRepositoryPort port) {
        return new ConsultarTodosAfectadosUseCase(port);
    }

    @Bean
    public ActualizarAfectadoUseCase actualizarAfectadoUseCase(AfectadoRepositoryPort port,
                                                               ClasificadorDeAmenazasService clasificador) {
        return new ActualizarAfectadoUseCase(port, clasificador);
    }
}