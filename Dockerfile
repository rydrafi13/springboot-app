# Use a lightweight OpenJDK image with Java 17, suitable for Spring Boot
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files first
# This ensures the mvnw script and its dependencies are available
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Copy the source code
COPY src ./src

# Make the Maven wrapper script executable
RUN chmod +x ./mvnw

# Build the Spring Boot application using Maven
# The -DskipTests flag skips running tests during the build
RUN ./mvnw clean package -DskipTests

# Expose the port that Spring Boot typically runs on (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
# The jar file name will be sample-app-0.0.1-SNAPSHOT.jar based on your pom.xml
CMD ["java", "-jar", "target/sample-app-0.0.1-SNAPSHOT.jar"]