# Evaluación Equipo Oliver — Sistema de Rastreo Mirakuru

Sistema de gestión de individuos afectados por el suero Mirakuru, desarrollado para el equipo de Oliver Queen bajo la dirección de Felicity Smoak. Construido con **Java + Spring Boot** aplicando **Domain Driven Design (DDD)**, **principios SOLID** y **Arquitectura Hexagonal**.

Estudiante: Luis Esteban Villegas Mendoza
---

## Arquitectura

El proyecto sigue una **Arquitectura Hexagonal** (Ports & Adapters), dividida en tres capas que garantizan el desacoplamiento total del dominio frente a frameworks y tecnologías externas.

```
com.equipo.oliver.app/
├── domain/                         ← Núcleo del sistema (sin dependencias externas)
│   ├── model/
│   │   ├── Afectado.java           ← Entidad principal del dominio
│   │   └── NivelRiesgo.java        ← Objeto de valor (enum)
│   ├── port/
│   │   └── AfectadoRepositoryPort.java  ← Puerto de salida (interfaz)
│   └── service/
│       └── ClasificadorDeAmenazasService.java  ← Servicio de dominio
│
├── application/                    ← Casos de uso (orquestación)
│   └── usecase/
│       ├── RegistrarAfectadoUseCase.java
│       ├── ConsultarAfectadoUseCase.java
│       ├── ConsultarTodosAfectadosUseCase.java
│       └── ActualizarAfectadoUseCase.java
│
└── infrastructure/                 ← Adaptadores (Spring, JPA, REST)
    ├── adapter/
    │   ├── persistence/
    │   │   ├── AfectadoEntity.java
    │   │   ├── AfectadoJpaRepository.java
    │   │   └── AfectadoRepositoryAdapter.java
    │   └── rest/
    │       ├── AfectadoController.java
    │       └── dto/
    │           ├── AfectadoRequestDTO.java
    │           └── AfectadoResponseDTO.java
    └── config/
        └── BeanConfig.java
```

---

## Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17+ | Lenguaje principal |
| Spring Boot | 3.x | Framework web y contexto |
| Spring Data JPA | — | Adaptador de persistencia |
| H2 Database | — | Base de datos en memoria |
| Lombok | — | Reducción de boilerplate |
| Maven | — | Gestión de dependencias |

---

## Cómo ejecutar

### Prerrequisitos
- Java 17 o superior
- Maven (incluido con el wrapper `mvnw`)

### Pasos

```bash
# Clonar el repositorio
git clone <url-del-repo>
cd evaluacion-equipo-oliver

# Ejecutar
./mvnw spring-boot:run
```

La aplicación arranca en `http://localhost:8080`

### Consola H2 (base de datos)
```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:oliverdb
User:     sa
Password: (vacío)
```

---

## Endpoints de la API

### Registrar un afectado
```http
POST /api/afectados
Content-Type: application/json

{
  "nombre": "Slade Wilson",
  "ubicacion": "Zona Norte",
  "cantidadSintomas": 11
}
```

### Consultar todos los afectados
```http
GET /api/afectados
```

### Consultar un afectado por ID
```http
GET /api/afectados/{id}
```

### Actualizar un afectado
```http
PUT /api/afectados/{id}
Content-Type: application/json

{
  "nombre": "Slade Wilson",
  "ubicacion": "Zona Sur",
  "cantidadSintomas": 4
}
```

---

## Reglas de negocio

### Clasificación automática de nivel de riesgo

El sistema asigna el nivel de riesgo automáticamente al registrar o actualizar un afectado, según la cantidad de síntomas reportados:

| Síntomas | Nivel de Riesgo |
|---|---|
| 0 – 2 | `BAJO` |
| 3 – 5 | `MEDIO` |
| 6 – 9 | `ALTO` |
| 10+ | `CRITICO` |

### Restricción nivel CRÍTICO
Un individuo no puede ser clasificado como `CRITICO` si tiene menos de 3 síntomas registrados. El sistema lanza una excepción en ese caso.

---

## Decisiones de diseño

### ¿Por qué `Afectado.java` no tiene anotaciones JPA?
El dominio debe ser independiente de cualquier framework. La entidad JPA (`AfectadoEntity`) vive en la capa de infraestructura y se mapea al modelo de dominio dentro del adaptador. Esto aplica el principio de **Inversión de Dependencias (SOLID-D)**.

### ¿Por qué existe `AfectadoRepositoryPort`?
Es el **puerto de salida** — una interfaz que el dominio define para expresar lo que necesita, sin conocer la implementación. El adaptador de persistencia (`AfectadoRepositoryAdapter`) es quien la implementa. Esto permite cambiar la base de datos sin tocar el dominio.

### ¿Por qué `BeanConfig` instancia los casos de uso manualmente?
Los casos de uso pertenecen a la capa de aplicación y no deben ser componentes de Spring (`@Service`). `BeanConfig` actúa como **punto de composición**, inyectando las dependencias sin contaminar las capas internas con anotaciones del framework.

### ¿Por qué `ClasificadorDeAmenazasService` es un servicio de dominio?
La lógica de clasificación por síntomas no pertenece a la entidad `Afectado` sola, ya que podría involucrar múltiples entidades en el futuro. Al encapsularla en un servicio de dominio, se respeta el **Principio de Responsabilidad Única (SOLID-S)**.

---

## Autores

Desarrollado como evaluación parcial del curso **Construcción de Software** — Tecnológico de Antioquia.

Aplicando: Domain Driven Design · Principios SOLID · Arquitectura Hexagonal · Spring Boot