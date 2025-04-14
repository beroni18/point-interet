FROM openjdk:21
COPY target/*.jar point-interet-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/point-interet-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082