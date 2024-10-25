FROM openjdk:21-jdk-slim
ADD /user/build/libs/*.jar user.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]