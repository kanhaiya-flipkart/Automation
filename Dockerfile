# Use a base image with Java 11 installed
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy the Java application JAR file to the container
COPY target/automation-testing-1.0-SNAPSHOT.jar my-app.jar

# Set the entry point command for the container
ENTRYPOINT ["java", "-jar", "my-app.jar"]


