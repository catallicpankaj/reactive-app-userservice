FROM openjdk:8-jre-slim

WORKDIR /usr/src/app

COPY target/ReactiveApp1-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/usr/src/app/ReactiveApp1-0.0.1-SNAPSHOT.jar"]