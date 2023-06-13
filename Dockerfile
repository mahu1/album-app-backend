FROM openjdk:16-alpine3.13
COPY target/*.jar album-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "album-app.jar"]