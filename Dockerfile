FROM maven:ibmjava-alpine as build-deps
RUN mkdir -p /app
WORKDIR /app
USER root
COPY . .
RUN mvn clean package

FROM openjdk:8-jdk-alpine
RUN mkdir -p /app
WORKDIR /app
COPY --from=build-deps /app/target/*.jar /app/app.jar
CMD java -jar app.jar
