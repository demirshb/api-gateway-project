FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR test
COPY --from=build target/*.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]