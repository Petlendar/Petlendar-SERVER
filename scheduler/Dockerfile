FROM openjdk:21-jdk-slim
ADD /scheduler/build/libs/*.jar scheduler.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/scheduler.jar"]