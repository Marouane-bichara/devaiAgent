Project: AI-Dev Agent — Handover / Developer Guide
=================================================

Purpose
-------
This document is intended to give a new team a fast, practical onboarding for the
devaiAgent Spring Boot project. It explains architecture, packages, important files,
required configuration, build and run procedures (local and Docker), where to place
the future Gemini integration, MapStruct usage, and other operational notes.

Checklist (what I will cover)
- Project overview and goals
- How to build & run locally (Maven) and with Docker (docker-compose)
- Important environment variables
- Key packages and files and what they do
- MapStruct, annotation processing and how to add mappers
- Where to add the Gemini API integration
- Notes: security, persistence, testing and troubleshooting

1) High-level overview
----------------------
This project is a Spring Boot backend for a SaaS platform that automates software
development using AI agents (Architect, Developer, etc.). The backend exposes
REST/WebSocket APIs, stores projects, logs and user/payment data, and orchestrates
AI provider calls (Gemini planned), file storage, and payment flows (Stripe, CIH).

Core functional domains (packages)
- ai: token usage, AI conversations, AI logs, provider clients (gemini package)
- architect: generation of development strategy, DB schema, endpoints
- developer: code generation jobs, build/test/debug execution logs
- project: project, versions, files, briefs
- user: user profile and account-related DTOs/entities
- security: authentication, roles, refresh tokens, AppUser entity
- chat: conversation and messages (WebSocket + persistence)
- storage: temporary file storage, stored artifacts
- delivery: delivery records, download links and artifacts
- payment: payments, webhook handling
- common: enums and BaseEntity used across modules
- config: Spring configuration classes (Jpa, Security, MapStruct, WebSocket)

2) Build & run
---------------
Prerequisites
- Java 17 JDK
- Maven 3.8+ (or use the included Maven wrapper: mvnw / mvnw.cmd)
- Docker & Docker Compose (for containerized MySQL + app)

Build using Maven
- Compile and package (skip tests in CI/dev builds if needed):
  mvn -DskipTests package

Run locally
- Use Spring Boot run for development:
  mvn spring-boot:run

Run with Docker (recommended for handover to ops)
- A Dockerfile and docker-compose.yml are included. To run the app and a MySQL
  service:
  docker-compose up --build

Notes about Docker Compose file (docker-compose.yml)
- Services defined: mysql (MySQL 8.4) and app (this Spring Boot application).
- Exposed ports: 3306 (MySQL), 8080 (app)
- Environment variables set in docker-compose for the app include:
  - SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD
  - JWT_SECRET
  - AI_PROVIDER (defaults to gemini) and GEMINI_API_KEY placeholder

3) Important environment variables and properties
- SPRING_DATASOURCE_URL / USER / PASSWORD — database connection
- JWT_SECRET — signing key for JWT (replace before production)
- SERVER_PORT — server port override (default 8080)
- APP_STORAGE_TEMP_DIRECTORY / DELIVERY_DIRECTORY — local storage paths
- AI_PROVIDER — provider type (gemini planned). See `ai/provider` package.
- GEMINI_API_KEY and GEMINI_MODEL — Gemini configuration entries
- STRIPE_SECRET_KEY and STRIPE_WEBHOOK_SECRET — stripe integration
- CIH_ENABLED and CIH_API_KEY — local banking integration

These variables are also referenced in src/main/resources/application.properties
via placeholders. In Docker Compose, several are already set for convenience.

4) Persistence and database migration
- Uses Spring Data JPA with MySQL (mysql-connector-j dependency). An H2
  dependency is included for tests/quick runs.
- Flyway is enabled (spring.flyway.locations = classpath:db/migration). Migration
  SQL files should go into src/main/resources/db/migration. Check existing
  migrations before altering.
- The project default is spring.jpa.hibernate.ddl-auto=update — for production
  prefer controlled migrations with Flyway and set ddl-auto to validate.

5) MapStruct (already configured)
- The pom.xml already contains MapStruct (org.mapstruct:mapstruct) and the
  annotation processor is listed in maven-compiler-plugin -> annotationProcessorPaths
  as mapstruct-processor.
- How to add mappers:
  - Create mapper interfaces in the relevant package, e.g. com.example.devaiagent.user.mapper
  - Use @org.mapstruct.Mapper(componentModel = "spring") so Spring can inject mappers
  - Example (conceptual):
    @Mapper(componentModel = "spring")
    public interface UserMapper { UserDto toDto(AppUser entity); }
  - No extra runtime dependency is required; the processor generates implementations
    at compile time under target/generated-sources/annotations.
- IntelliJ users: enable "Annotation Processing" in Settings → Build, Execution,
  Deployment → Compiler → Annotation Processors so MapStruct generated sources are
  visible in the IDE.

6) Where to add the Gemini API integration
- Project already contains ai/provider/gemini package with a `GeminiClient` and
  `GeminiProperties` (placeholders). To integrate Gemini:
  - Implement a provider client that follows a simple provider interface (create
    ai/provider/AIProvider or similar) and register it as a Spring bean.
  - Use configuration properties under `app.ai.gemini.*` (already read in
    application.properties) to store API key and model name.
  - Use the `AIProviderType` enum to add a `GEMINI` entry and a simple factory
    to choose the provider based on `app.ai.provider`.

7) Testing
- Unit tests: standard Spring Boot test dependency included (spring-boot-starter-test).
- For integration tests with MySQL, prefer using Testcontainers or the embedded
  H2 profile. There is an application-test.properties in src/test/resources.

8) Security
- The codebase includes a `security` package and a `AppUser` entity located at
  src/main/java/com/example/devaiagent/security/entity/AppUser.java.
- JWT libs are included (jjwt). The JWT secret is read from env/app property
  `app.security.jwt.secret` (application.properties key `app.security.jwt.secret`).
- SecurityConfig.java is present in src/main/java/com/example/devaiagent/config —
  it is a placeholder and must be filled with proper Spring Security configuration
  (authenticationManager, password encoder, filter chain, JWT token filters, etc.)

9) Storage and Delivery
- The storage package contains DTOs and entities for stored files and serves as
  temporary storage for generated project artifacts. Directories are configured
  with app.storage.* properties.
- Delivery package manages delivery records and artifacts; once payment is
  confirmed a DeliveryRecord + DownloadLink should be created and persisted.

10) Packages and representative files (quick map)
- com.example.devaiagent (application starter)
- com.example.devaiagent.config
  - MapStructConfig.java — placeholder bean config for MapStruct-related beans
  - JpaConfig.java — placeholder for JPA beans
  - SecurityConfig.java — placeholder; implement Spring Security here
  - WebSocketConfig.java — configure STOMP/WebSocket endpoints
- com.example.devaiagent.common
  - base/BaseEntity.java — common JPA base entity (id, createdAt, updatedAt)
  - enums — domain enums (ProjectStatus, UserRole, etc.)
- com.example.devaiagent.security
  - entity/AppUser.java — user entity (fields: fullName, email, password, balance, enabled, role)
  - entity/RefreshToken.java, Role.java — help security flows
  - dto/LoginRequest.java, RegisterRequest.java, AuthResponse.java — auth DTOs
- com.example.devaiagent.user
  - dto/UserProfileResponse.java, UpdateProfileRequest.java — user DTOs
- com.example.devaiagent.project
  - entity/Project.java, ProjectVersion.java, ProjectFile.java — project model
  - dto/ProjectCreateRequest.java, ProjectUpdateRequest.java, ProjectResponse.java
- com.example.devaiagent.architect
  - entity/DevelopmentStrategy, DatabaseSchema, DatabaseTable/Column, ApiEndpoint, UserStory
  - dto/*Dto — strategy response/requests and schema DTOs
- com.example.devaiagent.ai
  - entity/AiLog, AiConversation, TokenUsage — AI logging and quota tracking
  - dto/AiPromptRequest, AiCompletionResponse, AiLogResponse, TokenUsageResponse
  - provider/gemini/GeminiClient.java, GeminiProperties.java — place for Gemini integration
- com.example.devaiagent.developer
  - entity/GenerationJob, BuildExecution, DebugLog, TestExecution — autonomous dev jobs
  - dto/*Response / Request — job and execution DTOs
- com.example.devaiagent.chat
  - entity/ChatConversation, ChatMessage
  - dto/ChatMessageRequest, ChatMessageResponse, ChatConversationResponse
- com.example.devaiagent.storage
  - entity/StoredFile.java — persisted file meta
  - dto/FileUploadRequest, StoredFileResponse
- com.example.devaiagent.delivery
  - entity/DeliveryRecord, DownloadLink, DeliveryArtifact
  - dto/*Response — delivery DTOs
- com.example.devaiagent.payment
  - entity/Payment, PaymentWebhookEvent
  - dto/CheckoutRequest, PaymentResponse, WebhookEventRequest

11) Helpful development tips and conventions
- Keep DTOs and entities separated: DTOs under dto, entities under entity
- Use MapStruct for entity<->DTO mapping. Write mappers per domain package
  and name them *Mapper.
- Keep services stateless and annotate with @Service. Controllers in a web
  package or just under the domain package with suffix *Controller.
- For long-running agent jobs, use a separate executor (Spring TaskExecutor) and
  persist job state to Developer/GenerationJob entities so the system can resume.
- Be careful with file storage permissions and make temp directories configurable
  (see app.storage.* properties).

12) Troubleshooting
- If MapStruct generated sources aren’t visible in IDE: enable annotation processing,
  ensure maven-compiler-plugin configuration is present (pom.xml has it), and
  run `mvn clean compile` to generate sources.
- Database connection errors: check environment variables and MySQL container logs
  (docker-compose logs mysql). Ensure Flyway migrations are valid.
- JWT issues: ensure app.security.jwt.secret is set and tokens are signed with
  the same key the server uses.

13) Next tasks / recommended TODOs for the incoming team
- Implement SecurityConfig with JWT filter and password encoding
- Provide concrete MapStruct mappers for all entity/dto pairs
- Implement AIProvider interface and concrete Gemini provider (GeminiClient)
- Harden configuration: move secrets to environment / secret manager and set
  appropriate profiles for dev/production
- Add integration tests for payment webhooks (Stripe + CIH) using Testcontainers
- Review docker-compose volumes and production deployment strategy (Kubernetes?)

Files to review first (priority)
1. src/main/java/com/example/devaiagent/config/SecurityConfig.java
2. src/main/java/com/example/devaiagent/ai/provider/gemini/GeminiClient.java
3. src/main/java/com/example/devaiagent/developer/* (GenerationJob, BuildExecution)
4. src/main/resources/application.properties and src/main/resources/db/migration
5. pom.xml — check dependency versions for security updates

Contact & Handover notes
- The codebase contains TODO placeholders. Search for `TODO` markers to find
  unfinished parts.
- I recommend running the app locally with Docker Compose first to confirm the
  database and Flyway migrations are healthy, then proceed to implement security
  and provider integrations.

Appendix A — Quick commands
- Build jar: mvn -DskipTests package
- Run: mvn spring-boot:run
- Docker (build & run): docker-compose up --build
- Clean: mvn clean
- Run tests: mvn test

Appendix B — Key files (explicit list)
- pom.xml — maven dependencies and MapStruct annotationProcessor already configured
- Dockerfile — multi-stage build using Maven and Eclipse Temurin JRE
- docker-compose.yml — mysql + app service + env examples
- application.properties — runtime configuration and placeholders
- src/main/java/com/example/devaiagent/DevaiAgentApplication.java — Spring Boot entry
- src/main/java/com/example/devaiagent/config/* — configuration placeholders
- src/main/java/com/example/devaiagent/security/entity/AppUser.java — user entity

End of document. Use this file as a living handover document and update as you
complete the TODOs and implement missing features.

