FROM openjdk:21-jdk-slim
ADD /board/build/libs/*.jar board.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/board.jar"]