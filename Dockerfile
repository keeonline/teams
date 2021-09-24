FROM openjdk:11-jdk-alpine
COPY build/libs/teams-0.0.1-SNAPSHOT.jar teams-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","teams-0.0.1-SNAPSHOT.jar"]