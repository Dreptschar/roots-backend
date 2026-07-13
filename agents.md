# Project Brief

## Verified project facts

- Project name: `roots-backend`
- Maven artifact coordinates:
  - groupId: `com.dreptschar`
  - artifactId: `roots-backend`
  - version: `1.0-SNAPSHOT`
- Build system: Maven
- Framework: Spring Boot
- Spring Boot parent version: `4.1.0`
- Language level: Java `26`
- Current dependencies:
  - `spring-boot-starter-web`
  - `spring-boot-starter-validation`
  - `mapstruct`
  - `spring-boot-starter-test` for tests
- Current build plugins:
  - `openapi-generator-maven-plugin`
  - `build-helper-maven-plugin`
  - `maven-compiler-plugin`
- Current description from `pom.xml`: “Spring Boot backend exposing REST endpoints”
- Repository state at inspection time: active backend implementation with generated OpenAPI contracts, handwritten adapters, and tests

## Product scope

This repository is a self-hosted backend for managing house plants, their care actions, recurring care plans, and related metadata.

## Confirmed decisions

- Core domain entities for v1:
  - `Plant`
  - `Room`
  - `ActionType`
  - `ActionPlan`
  - `PlantAction`
- Plant model direction for v1:
  - `name`
  - `species`
  - `room`
  - `actionPlans`
  - `notes`
  - `photo`
  - `createdAt`
- Features for version 1:
  - create plants
  - edit plants
  - track watering
  - track fertilizing
  - dashboard
  - file/image upload for plant photos
- Authentication and authorization are not part of the first version.
- Notifications for version 1 use a Discord webhook.
- Pagination, filtering, sorting, and search are not required from day one.

## Current domain direction

- Household scope: single household per installation
- Action model direction:
  - `action-task`
  - `action-schedule`
  - `action`
- The goal is to avoid hard-coding only watering intervals so new action types can be added more easily later.
- First-version scheduling uses simple day-based intervals.

## Architecture

This project uses hexagonal architecture.

- Domain code stays in `com.dreptschar.roots_backend.domain` and contains the business model and business services.
- Inbound adapters live in `com.dreptschar.roots_backend.adapter.inbound.rest` and expose HTTP controllers.
- Generated OpenAPI contracts live in `com.dreptschar.roots_backend.api` and `com.dreptschar.roots_backend.model`, under `src-gen/openapi`.
- Outbound adapters and persistence code should stay behind ports and adapters, not leak into the domain.
- Controllers should depend on domain services and mapper interfaces, not on infrastructure details.

### API design

- API style: REST
- OpenAPI contract source: `src/main/resources/openapi.yaml`
- Generated API interfaces are used by handwritten controller implementations
- The OpenAPI file is the source of truth for frontend/backend communication.
- All frontend/backend communication should go through the generated API interfaces and generated model classes.

### Persistence and infrastructure

- Database choice: PostgreSQL
- Persistence layer: JDBC / Spring JDBC (`JdbcTemplate` style)
- Migration / schema history: Flyway
- Docker support: yes, using a local PostgreSQL container
- Local development profile: `local`

### Schema decisions made so far

- `action_types` stores all supported action kinds, including defaults like watering and fertilizing.
- `plant_action_plans` stores recurring plans for a plant and uses simple `interval_days`.
- `plant_actions` stores actual action history for a plant.
- `plants.photo_path` stores the path or URI to the image.
- `rooms` is a separate table.
- Auth tables are not part of v1.

### Technical conventions

- Tests should use `// Arrange`, `// Act`, `// Assert` comments.
- Tests should use AssertJ for assertions.
- Test coverage should include unit, integration, and repository tests.
- Package structure uses hexagonal architecture:
  - domain in `com.dreptschar.roots_backend.domain`
  - inbound REST adapters in `com.dreptschar.roots_backend.adapter.inbound.rest`
  - generated API contracts in `com.dreptschar.roots_backend.api`
  - generated models in `com.dreptschar.roots_backend.model`
- Generated sources live in `src-gen/openapi`.

### Non-functional requirements

- No additional non-functional constraints have been finalized yet.

## Stack decisions made so far

- Persistence: PostgreSQL
- Schema migration: Flyway
- Flyway PostgreSQL module: `flyway-database-postgresql`
- Database access: JDBC / Spring JDBC
- Authentication foundation: Spring Security
- Existing API style: REST
- API contract source: `src/main/resources/openapi.yaml`
- REST adapter package: `com.dreptschar.roots_backend.adapter.inbound.rest`
- Generated API contract package: `com.dreptschar.roots_backend.api`
- Generated model package: `com.dreptschar.roots_backend.model`
- Generated sources location: `src-gen/openapi`
- Domain package: `com.dreptschar.roots_backend.domain`
- Generated API interfaces are used by handwritten controller implementations
- Frontend/backend communication must follow the OpenAPI-generated classes and not bypass them with ad hoc DTOs.
- First implemented endpoint: `GET /plants`
- Plants list response is a generated OpenAPI model mapped from `Plant`
- Mapper framework: MapStruct

## Local development setup

- Docker Compose provides a PostgreSQL 17 Alpine container.
- Local database name: `plant_manager`
- Local database user: `plant_manager`
- Local database password: `plant_manager`
- Spring profile for local development: `local`
- Local datasource URL: `jdbc:postgresql://localhost:5432/plant_manager`
- Default Spring profile for now: `local`

## Open questions

- Should this eventually remain a pure REST API, or should it grow webhooks / SSE / GraphQL?
- What should be the main endpoint set beyond plants, rooms, action types, action plans, and plant actions?
- Do you want logging, auditing, or a standardized error format?
