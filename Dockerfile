FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/orders-0.0.1-SNAPSHOT.jar orders.jar
ENTRYPOINT ["java","-jar","/orders.jar"]
