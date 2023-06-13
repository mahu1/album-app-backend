FROM openjdk:latest
COPY target/*.jar album-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "album-app.jar"]