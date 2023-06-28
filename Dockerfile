FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/album-app-backend-0.0.1-SNAPSHOT.jar album-app.jar
ENTRYPOINT ["java", "-jar", "album-app.jar"]