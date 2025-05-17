FROM openjdk:21-jdk-slim

LABEL "org.opencontainers.image.authors"="jinn9"

# Add the application's jar to the image
COPY target/product-0.0.1-SNAPSHOT.jar product-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "product-0.0.1-SNAPSHOT.jar"]