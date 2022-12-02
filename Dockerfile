FROM openjdk:11
ADD target/super-heroes-0.0.1-SNAPSHOT.jar docker-app
ENTRYPOINT ["java", "-jar", "docker-app"]