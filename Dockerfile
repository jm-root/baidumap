FROM ibmjava-alpine as build-deps
RUN mkdir -p /app
WORKDIR /app
USER root
COPY . .
RUN mvn clean package

FROM java:alpine
RUN mkdir -p /app
WORKDIR /app
COPY --from=build-deps /app/target/*.jar /app/app.jar
RUN java -jar app.jar
