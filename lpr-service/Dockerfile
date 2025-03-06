# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from target folder
COPY target/*.jar app.jar

# Expose the port (change if needed)
EXPOSE 8081

# Run the application
#CMD ["java", "-cp", "app.jar", "com.parking.lpr.LprServiceServer"]
CMD ["java", "-jar", "app.jar"]
