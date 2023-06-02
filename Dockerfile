FROM openjdk:19-jdk-alpine
MAINTAINER Alexey Romanov
COPY target/pastebox-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java","-jar","/pastebox.jar"]
