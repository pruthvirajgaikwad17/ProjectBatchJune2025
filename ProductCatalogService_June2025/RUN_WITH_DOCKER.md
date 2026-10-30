# Run ProductCatalogService with Docker

Only Docker Desktop is needed. No Java, MySQL, Redis or IntelliJ.

## Files
| File | Purpose |
|---|---|
| `Dockerfile` | Builds the jar with Maven, then runs it on Java 21 |
| `Dockerfile.prebuilt` | Runs the jar already in `target/` (no Maven download) |
| `docker-compose.yml` | Starts MySQL, Redis and the app |
| `.env.example` | Sample settings; copy it to `.env` |
| `.dockerignore` | Keeps IDE files and `.env` out of the image |

## Setup
1. Install Docker Desktop and wait for **Engine running**.
2. Copy `.env.example` to `.env` and set `DB_PASSWORD`.
3. If Maven is blocked on your network, add `DOCKERFILE=Dockerfile.prebuilt`
   to `.env` and put the jar in `target/`.

## Run
```
docker compose up --build      # first time / after code change
docker compose up -d           # run in background
docker compose ps              # check status
docker compose logs -f app     # see app logs
docker compose down            # stop
docker compose down -v         # stop and wipe database
```
The app runs at http://localhost:8080

## Endpoints
| Method | URL |
|---|---|
| GET | /products |
| GET | /products/{id} |
| POST | /products |
| PUT | /products/{id} |
| POST | /search |

## Notes
- Eureka is disabled in Docker (`EUREKA_CLIENT_ENABLED=false`).
- `GET /products` calls the FakeStore API, so it needs internet access from Docker.
- `GET /products/{id}` calls `userauthservice`, which must be running.

## Troubleshooting
- **port is already allocated**: close whatever uses 3306, 6379 or 8080.
- **Access denied for user root**: the password changed after the first run. Run `docker compose down -v`.
- **Maven "Network is unreachable"**: use `DOCKERFILE=Dockerfile.prebuilt`.