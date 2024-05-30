FROM maven:3.6.3-openjdk-17-slim as builder
WORKDIR /app
COPY pom.xml .

RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn clean install

FROM openjdk:17-slim

COPY --from=builder /app/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]