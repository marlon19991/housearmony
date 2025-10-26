# Multi-stage build for Spring Boot application
# This Dockerfile is for deploying from the repository root

# Stage 1: Build
FROM gradle:8-jdk17 AS build
WORKDIR /app

# Copy backend files
COPY backend/build.gradle backend/settings.gradle ./
COPY backend/gradle ./gradle

# Download dependencies (cached layer)
RUN gradle dependencies --no-daemon || true

# Copy source code
COPY backend/src ./src

# Build the application
RUN gradle bootJar --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port (Railway will override with PORT env var)
EXPOSE 8080

# Run the application with production profile
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
