FROM maven:3.8.1-openjdk-11-slim AS build
WORKDIR /app
COPY src ./src
COPY src/pom.xml .
RUN mvn clean -DskipTests
RUN mvn package -DskipTests
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/server-1.0-SNAPSHOT.jar ./server.jar
EXPOSE 1099
CMD ["java", "-jar", "/app/server.jar"]