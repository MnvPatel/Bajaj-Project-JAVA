# Building Executable JAR File

## Prerequisites
- Java 17 or higher installed
- Maven installed (or use Maven Wrapper)

## Method 1: Using Maven (if installed)

### Step 1: Navigate to Project Directory
```bash
cd D:\Projects\Bajaj
```

### Step 2: Build the Executable JAR
```bash
mvn clean package
```

### Step 3: Find the JAR File
The executable JAR will be created at:
```
target\bfh-java-test-1.0.0.jar
```

### Step 4: Run the Executable JAR
```bash
java -jar target\bfh-java-test-1.0.0.jar
```

## Method 2: Using Maven Wrapper (No Maven Installation Required)

### Step 1: Install Maven Wrapper
First, you need Maven installed temporarily to generate the wrapper:
```bash
mvn wrapper:wrapper
```

Or download Maven Wrapper manually (see alternative below).

### Step 2: Use Maven Wrapper to Build
```bash
.\mvnw.cmd clean package
```

### Step 3: Run the JAR
```bash
java -jar target\bfh-java-test-1.0.0.jar
```

## Method 3: Quick Install Maven (Windows)

### Option A: Using Chocolatey (if installed)
```bash
choco install maven
```

### Option B: Manual Installation
1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract to a folder (e.g., `C:\Program Files\Apache\maven`)
3. Add to PATH:
   - Open System Properties → Environment Variables
   - Add `C:\Program Files\Apache\maven\bin` to PATH
4. Verify: `mvn -version`

## JAR File Details

**Location:** `target\bfh-java-test-1.0.0.jar`

**Size:** Approximately 15-20 MB (includes all dependencies)

**Type:** Executable JAR (Fat JAR) - contains all dependencies

**Run Command:**
```bash
java -jar target\bfh-java-test-1.0.0.jar
```

## Verify JAR is Executable

### Check JAR Contents
```bash
jar -tf target\bfh-java-test-1.0.0.jar | findstr "MANIFEST"
```

### Check Main Class
```bash
jar -xf target\bfh-java-test-1.0.0.jar META-INF/MANIFEST.MF
type META-INF\MANIFEST.MF
```

Should show: `Main-Class: org.springframework.boot.loader.JarLauncher`

## Troubleshooting

### Error: "Could not find or load main class"
**Solution:** Ensure you're using the JAR from `target` folder, not a different one.

### Error: "mvn: command not found"
**Solution:** Install Maven or use Docker to build:
```bash
docker-compose up --build
```

### Build Fails
**Solution:** Clean and rebuild:
```bash
mvn clean install -U
```

## Distribution

To share the application:
1. Build the JAR: `mvn clean package`
2. Share the file: `target\bfh-java-test-1.0.0.jar`
3. Recipient needs: Java 17+ installed
4. Run: `java -jar bfh-java-test-1.0.0.jar`

The JAR is self-contained and includes all dependencies!


