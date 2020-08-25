FROM openjdk:13

COPY build/libs/*.jar movies-api.jar
ENTRYPOINT ["java", "-jar", "movies-api.jar"]