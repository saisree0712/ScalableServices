# # Use an OpenJDK base image
# FROM openjdk:17-jdk-slim

# # Set the working directory inside the container

# # Copy the Spring Boot JAR file into the container
# ADD target/restaurant-service.jar restaurant-service.jar

# # Expose the application port
# EXPOSE 5002

# # Command to run the application
# ENTRYPOINT ["java", "-jar", "/restaurant-service.jar"]

# Stage 1: Build the JAR file
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Build the project and create the JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Copy the built JAR file from the build stage
COPY --from=build /app/target/restaurant-service.jar restaurant-service.jar

# Expose the application port
EXPOSE 5000

# Command to run the application
ENTRYPOINT ["java", "-jar", "/restaurant-service.jar"]