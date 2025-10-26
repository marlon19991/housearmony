# Multi-stage build for Spring Boot application

# Stage 1: Build
FROM gradle:8-jdk17 AS build
WORKDIR /app

# Copy gradle files for dependency caching
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies (cached layer)
RUN gradle dependencies --no-daemon || true

# Copy source code
COPY src ./src

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

# Run the application
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
