FROM openjdk:17-jdk-slim

MAINTAINER yoki

#Add the application jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar


#execute the appliaction
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]