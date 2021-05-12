FROM maven:3.8.1-jdk-11 AS BUILD
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/
RUN mvn clean package

FROM openjdk:11
WORKDIR /app
COPY image /image
COPY --from=BUILD /build/target/*.jar clothshop-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar","clothshop-0.0.1-SNAPSHOT.jar"]