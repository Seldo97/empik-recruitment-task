# JDK platform
FROM openjdk:21-jdk-slim

# catalog
WORKDIR /app

# jar file name argument
ARG JAR_FILE=marcinolek-0.0.1.jar

# copy jar to container
COPY target/${JAR_FILE} /app/marcinolek.jar

# app port
EXPOSE 9090

# app run command
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "marcinolek.jar"]