FROM maven:3.9.4 as build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependancy:resolve
COPY . /app
RUN mvn clean
RUN mvn package

FROM openjdk:17
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]