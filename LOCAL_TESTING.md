# Local Testing Instructions

## Prerequisites

### Step 1: Check Java Installation
```bash
java -version
```
**Required:** Java 17 or higher

If not installed, download from: https://adoptium.net/

### Step 2: Check Maven Installation
```bash
mvn -version
```
**Required:** Maven 3.6 or higher

If not installed, download from: https://maven.apache.org/download.cgi

## Local Testing Steps

### Step 1: Navigate to Project Directory
```bash
cd D:\Projects\Bajaj
```

### Step 2: Clean and Build the Application
```bash
mvn clean package
```
This will:
- Compile the Java code
- Run tests (if any)
- Create a JAR file in the `target` directory

### Step 3: Run the Application
```bash
java -jar target/bfh-java-test-1.0.0.jar
```

**OR** run directly with Maven:
```bash
mvn spring-boot:run
```

### Step 4: Verify Output
You should see:
- Spring Boot startup logs
- Application executing the webhook request
- Success message: "Solution submitted successfully: 200 OK" (or similar status code)
- Any response body from the server

## Expected Output

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

... [Spring Boot logs] ...

Solution submitted successfully: 200 OK
Response: [response body if any]
```

## Troubleshooting

### Error: "mvn: command not found"
**Solution:** Install Maven and add it to your PATH

### Error: "java: command not found" or wrong Java version
**Solution:** 
- Install Java 17 or higher
- Set JAVA_HOME environment variable
- Verify with: `java -version`

### Error: "Failed to get access token"
**Solution:** 
- Check internet connection
- Verify the API endpoint is accessible
- Check if the request body format is correct

### Error: "401 Unauthorized"
**Solution:**
- Verify the accessToken is being extracted correctly
- Check if the Authorization header format is correct
- Review the error response body for details

### Build Error: "Could not resolve dependencies"
**Solution:**
```bash
mvn clean install -U
```
The `-U` flag forces Maven to update dependencies.

## Quick Test Commands

### Build only (skip tests)
```bash
mvn clean package -DskipTests
```

### Run with debug logging
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"
```

### Check if JAR was created
```bash
dir target\*.jar
```
or on Linux/Mac:
```bash
ls target/*.jar
```

## Next Steps

Once local testing is successful:
1. Test the application locally to ensure it works
2. Then proceed with Docker build: `docker-compose up --build`


