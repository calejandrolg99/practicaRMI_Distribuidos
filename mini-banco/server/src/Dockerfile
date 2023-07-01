FROM maven:3.8.1-openjdk-11-slim AS build
WORKDIR /app
COPY src ./src
COPY src/pom.xml .
RUN mvn clean -DskipTests
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/server-1.0-SNAPSHOT.jar ./server.jar
COPY --from=build /root/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.12.5/jackson-databind-2.12.5.jar ./jackson/
COPY --from=build /root/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.12.5/jackson-core-2.12.5.jar ./jackson/
COPY --from=build /root/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.12.5/jackson-annotations-2.12.5.jar ./jackson/
RUN touch /app/data.json

EXPOSE 1099
ENTRYPOINT ["java", "-cp", "/app/server.jar:/app/jackson/*", "com.example.Server"]