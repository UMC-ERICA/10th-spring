# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Build
./gradlew build

# Run application
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "umc.server.SomeTest"
```

**Required environment variables** (set before running):
- `DB_URL` — JDBC URL, e.g. `jdbc:mysql://localhost:3306/mydb`
- `DB_USER` — MySQL username
- `DB_PW` — MySQL password

Swagger UI is available at `/swagger-ui/index.html` when the app is running.

## Architecture

Spring Boot 4.0.3 / Java 17 / MySQL / JPA (Hibernate). The project follows a **domain-driven package structure**:

```
umc.server
├── domain/
│   ├── member/        # Member registration, profile, home, mypage
│   ├── store/         # Store CRUD and store-owned missions
│   ├── mission/       # Mission lifecycle, member-mission tracking
│   ├── review/        # Store reviews
│   ├── inquiry/       # Customer inquiries
│   └── notification/  # Alarms and alarm settings
└── global/
    ├── apiPayload/    # ApiResponse<T>, PageResponse<T>, error/success codes
    ├── entity/        # BaseEntity (audit + soft delete)
    └── config/        # JpaConfig, SwaggerConfig
```

Each domain package contains: `controller/`, `service/`, `repository/`, `entity/`, `dto/` (request & response), `converter/`, `enums/`, `exception/`.

## Key Conventions

### Response wrapper
All controllers return `ApiResponse<T>`. Use the static factory methods:
- `ApiResponse.onSuccess(result)` — HTTP 200 with generic OK code
- `ApiResponse.onSuccess(code, result)` — HTTP 200 with a domain-specific `BaseSuccessCode`
- `ApiResponse.onFailure(code, result)` — error response

For paginated results, wrap `Page<T>` in `PageResponse.from(page)` before passing to `ApiResponse.onSuccess(...)`.

### Error handling
- Each domain has its own `XxxException extends RuntimeException` holding a `BaseErrorCode`.
- Error codes are enums implementing `BaseErrorCode` (e.g., `StoreErrorCode`, `GeneralErrorCode`). Each entry declares `HttpStatus status`, `String code` (e.g., `"STORE404_1"`), and `String message`.
- Throw domain exceptions directly from services; there is no global `@ControllerAdvice` handler yet — add one if needed.

### Soft delete
`BaseEntity` provides `createdAt`, `updatedAt`, `deletedAt`, `softDelete()`, and `isDeleted()`.  
Entities use `@SQLDelete(sql = "UPDATE ... SET deleted_at = NOW() WHERE ... = ?")` and `@SQLRestriction("deleted_at IS NULL")` so that Hibernate automatically filters deleted rows. JPQL queries that join across entities must add explicit `deletedAt IS NULL` predicates for joined tables.

### Converter pattern
Entity↔DTO conversion belongs in stateless `XxxConverter` classes with static methods (no `@Component`). Services call converters; controllers do not touch entities directly.

### DTO style
Request/Response DTOs are Java `record`s or inner `record`s grouped in a wrapper class (e.g., `MissionReqDTO.CreateMission`). Response projections used in `@Query` JPQL must have a matching all-args constructor.
