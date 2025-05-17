FROM openjdk:21-jdk-slim

LABEL "org.opencontainers.image.authors"="jinn9"

# Add the application's jar to the image
COPY target/order-0.0.1-SNAPSHOT.jar order-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "order-0.0.1-SNAPSHOT.jar"]