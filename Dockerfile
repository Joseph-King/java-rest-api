# ----- BUILD -----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace


# COPY src and pom.xml then BUILD
COPY src ./src
COPY pom.xml .
RUN mvn clean install -Dmaven.test.skip=true

# ----- RUNTIME -----
FROM eclipse-temurin:21-jre

WORKDIR /app

# COPY jar from BUILD
COPY --from=build /workspace/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]