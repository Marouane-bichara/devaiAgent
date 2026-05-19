# AI-Dev Agent
Spring Boot backend skeleton for the AI-Dev Agent SaaS.
## Structure
- `common`: base classes and shared enums
- `config`: application configuration placeholders
- `security`: authentication models
- `user`: client profile models
- `project`: project and delivery lifecycle models
- `architect`: strategy and schema generation models
- `developer`: build/test/debug workflow models
- `chat`: real-time conversation models
- `payment`: Stripe/CIH payment models
- `delivery`: secure release models
- `storage`: temporary file storage models
- `ai`: AI orchestration models and Gemini slot
## Run with Docker
```bash
docker compose up --build
```
