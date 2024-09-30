FROM openjdk:17-jdk-slim
ADD /gateway/build/libs/*.jar gateway.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar"]