# Use official OpenJDK image
FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl
# Set working directory inside container
WORKDIR /app

# Copy the JAR file from target folder
COPY target/*.jar app.jar

# Expose the port (change if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
