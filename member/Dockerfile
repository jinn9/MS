FROM openjdk:21-jdk-slim

LABEL "org.opencontainers.image.authors"="jinn9"

# Add the application's jar to the image
COPY target/member-0.0.1-SNAPSHOT.jar member-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "member-0.0.1-SNAPSHOT.jar"]