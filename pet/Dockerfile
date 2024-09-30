FROM openjdk:17-jdk-slim
ADD /pet/build/libs/*.jar pet.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pet.jar"]