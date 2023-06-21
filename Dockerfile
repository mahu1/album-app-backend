FROM openjdk:17-jdk-alpine
COPY target/*.jar album-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "album-app.jar"]