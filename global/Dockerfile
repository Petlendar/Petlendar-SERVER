FROM openjdk:21-jdk-slim
ADD /global/build/libs/*.jar global.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/global.jar"]