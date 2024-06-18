FROM eclipse-temurin:21

COPY target/shop-hub-0.0.1-SNAPSHOT.jar shop-hub-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/shop-hub-0.0.1-SNAPSHOT.jar"]