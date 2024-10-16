# Step 1: Build the application using a Gradle image with JDK 17
FROM gradle:8.8-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Step 2: Create a new container with JRE and copy the built application
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/build/libs/com.example.demo-1.0.0.jar /app/com.example.demo-1.0.0.jar

# Step 3: Set the default command to run the application
CMD ["java", "-jar", "/app/com.example.demo-1.0.0.jar"]