FROM openjdk:21-jdk-slim
ADD /account/build/libs/*.jar account.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/account.jar"]