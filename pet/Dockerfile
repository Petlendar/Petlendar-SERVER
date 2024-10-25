FROM openjdk:21-jdk-slim
ADD /pet/build/libs/*.jar pet.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pet.jar"]