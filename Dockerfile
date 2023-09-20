FROM openjdk:20-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/album-app-backend-0.0.1-SNAPSHOT.jar album-app.jar
ENTRYPOINT ["java", "-jar", "album-app.jar"]