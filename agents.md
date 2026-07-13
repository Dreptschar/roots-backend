# Project Brief

## Verified project facts

- Project name: `PlantManagerBackend`
- Maven artifact coordinates:
  - groupId: `com.dreptschar`
  - artifactId: `PlantManagerBackend`
  - version: `1.0-SNAPSHOT`
- Build system: Maven
- Framework: Spring Boot
- Spring Boot parent version: `4.1.0`
- Language level: Java `26`
- Current dependencies:
  - `spring-boot-starter-web`
  - `spring-boot-starter-validation`
  - `spring-boot-starter-test` for tests
- Current description from `pom.xml`: “Spring Boot backend exposing REST endpoints”
- Repository state at inspection time: very early-stage / mostly empty aside from build and IDE metadata

## Working assumption

This repository is intended to become a backend service for a web frontend that lets users manage their house plants.
The project is intended for self-hosting and open-source use.

## Questions to finalize the project spec

### Product scope

1. What is the exact purpose of the application?
2. Intended users: people who have houseplants and want software to manage watering schedules and track when plants were last watered, fertilized, and similar care actions.
3. What problem should it solve better than a generic CRUD backend?

### Core domain

4. Additional entities needed so far:
   - `Room`
   - plant action history / activity history
5. What fields should a `Plant` have?
6. Are there relationships between plants and other entities?

### Features

7. Must-have version 1 features:
   - create plants
   - edit plants
   - track watering
   - track fertilizing
   - dashboard
8. Authentication and authorization are required.
9. Scheduling and notifications are required for version 1.
10. User notification channel for version 1: Discord webhook.
11. File/image upload for plant photos is required.

## Current domain direction

- Household scope: single household per installation
- Plant model for version 1:
  - `name`
  - `species`
  - `room`
  - `actionPlans` as a generic list for future extensibility
  - `notes`
  - `photo`
  - `createdAt`
- Action model direction:
  - `action-task`
  - `action-schedule`
  - `action`
- The goal is to avoid hard-coding only watering intervals so new action types can be added more easily later.
- First-version scheduling uses simple day-based intervals.

### API design

11. Should this be a pure REST API, or do you also want webhooks / SSE / GraphQL?
12. What should the main endpoints be?
13. Do you want pagination, filtering, sorting, and search from day one?

### Persistence and infrastructure

14. Database choice: PostgreSQL.
15. Persistence layer: JDBC / Spring JDBC (`JdbcTemplate` style).
16. Migration / schema history: Flyway.
17. Docker support: yes, using a local PostgreSQL container.
18. Will this run locally only, or be deployed somewhere?

### Schema decisions made so far

- `action_types` stores all supported action kinds, including defaults like watering and fertilizing.
- `plant_action_plans` stores recurring plans for a plant and uses simple `interval_days`.
- `plant_actions` stores actual action history for a plant.
- `plants.photo_path` stores the path or URI to the image.
- `rooms` is a separate table.
- Auth tables are delayed for now.

### Technical conventions

19. Any preferred package structure?
20. Any coding style or validation conventions I should follow?
21. Do you want OpenAPI/Swagger documentation?
22. What test level do you want: unit, integration, controller, repository?

### Non-functional requirements

23. Any performance, security, or privacy constraints?
24. Do you need logging, auditing, or error-format standards?

## Stack decisions made so far

- Persistence: PostgreSQL
- Schema migration: Flyway
- Flyway PostgreSQL module: `flyway-database-postgresql`
- Database access: JDBC / Spring JDBC
- Authentication foundation: Spring Security
- Existing API style: REST
- API contract source: `src/main/resources/openapi.yaml`
- REST adapter package: `com.dreptschar.plantmanagerbackend.adapter.outbound.rest`
- API contract package: `com.dreptschar.plantmanagerbackend.adapter.outbound.rest.api`
- Domain package: `com.dreptschar.plantmanagerbackend.domain`
- Generated controller behavior: return `501 Not Implemented` until business logic is implemented
- First implemented endpoint: `GET /plants`
- Plants list response is currently a simple summary DTO

## Local development setup

- Docker Compose provides a PostgreSQL 17 Alpine container.
- Local database name: `plant_manager`
- Local database user: `plant_manager`
- Local database password: `plant_manager`
- Spring profile for local development: `local`
- Local datasource URL: `jdbc:postgresql://localhost:5432/plant_manager`
- Default Spring profile for now: `local`

## Fill-in section

Once you answer the questions above, I will turn this file into the project source of truth and keep it updated as the implementation evolves.
