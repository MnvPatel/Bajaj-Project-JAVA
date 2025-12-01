# Docker Setup Instructions

## Prerequisites
- Docker Desktop installed and running on your system
- Docker Compose installed (optional, for easier management)

## Important: Start Docker Desktop First!

**Before running any Docker commands, make sure Docker Desktop is running:**
1. Open Docker Desktop application on Windows
2. Wait until you see "Docker Desktop is running" in the system tray
3. Verify Docker is running by executing: `docker ps`

## Method 1: Using Docker Commands

### Step 1: Navigate to Project Directory
```bash
cd D:\Projects\Bajaj
```

### Step 2: Build the Docker Image
```bash
docker build -t bfh-java-test .
```

### Step 3: Run the Container
```bash
docker run bfh-java-test
```

### Step 4: (Optional) Run in Detached Mode
```bash
docker run -d bfh-java-test
```

### Step 5: (Optional) View Logs
```bash
docker logs <container-id>
```

## Method 2: Using Docker Compose (Recommended)

### Step 1: Navigate to Project Directory
```bash
cd D:\Projects\Bajaj
```

### Step 2: Build and Run
```bash
docker-compose up --build
```

### Step 3: Run in Detached Mode
```bash
docker-compose up -d --build
```

### Step 4: View Logs
```bash
docker-compose logs -f
```

### Step 5: Stop the Container
```bash
docker-compose down
```

## Verification

After running, you should see output indicating:
- The webhook request was sent successfully
- The solution was submitted successfully
- HTTP status code confirmation

## Troubleshooting

### Error: "The system cannot find the file specified" or "unable to get image"
**Solution:** Docker Desktop is not running. 
1. Open Docker Desktop application
2. Wait for it to fully start (check system tray icon)
3. Verify with: `docker ps`
4. Then retry your command

### Check if Docker is running
```bash
docker --version
docker-compose --version
docker ps
```

### View running containers
```bash
docker ps
```

### View all containers (including stopped)
```bash
docker ps -a
```

### Remove old containers/images
```bash
docker rm <container-id>
docker rmi bfh-java-test
```

