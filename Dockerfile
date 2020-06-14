FROM openjdk:8-jdk-alpine

COPY ./target/*.jar ./

ENTRYPOINT ["java","-jar","/AnimalHelperService-0.0.1-SNAPSHOT.jar"]