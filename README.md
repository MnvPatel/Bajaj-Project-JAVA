# BFH Java Test Application

Spring Boot application that automatically generates a webhook, constructs a SQL query, and submits the solution via REST API.

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (for building)
- Docker (optional, for containerized deployment)

## Quick Start

### Build the Application

```bash
mvn clean package
```

### Run the Application

**Option 1: Run JAR directly**
```bash
java -jar target/bfh-java-test-1.0.0.jar
```

**Option 2: Run with Maven**
```bash
mvn spring-boot:run
```

**Option 3: Run with Docker**
```bash
docker-compose up --build
```

## How It Works

1. On startup, sends a POST request to generate a webhook and receive an access token
2. Constructs a SQL query to solve the given problem
3. Submits the solution via POST request with JWT authorization

## Project Structure

```
.
├── src/main/java/com/bajaj/
│   └── BfhJavaTestApplication.java    # Main application class
├── pom.xml                             # Maven dependencies
├── Dockerfile                          # Docker configuration
├── docker-compose.yml                  # Docker Compose configuration
└── README.md                           # This file
```

## Technologies

- Spring Boot 3.2.0
- Java 17
- RestTemplate (HTTP client)
- Jackson (JSON processing)
- Maven (build tool)

## Output

The application executes automatically on startup and displays:
- Spring Boot startup logs
- Success status of webhook generation
- Success status of solution submission

## License

This project is created for coding qualification test purposes.
