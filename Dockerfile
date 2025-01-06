FROM maven:3.8.8-amazoncorretto-21 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./produtos.jar

EXPOSE 8080

ENV DATASOURCE_URL='jdbc:postgresql://postgres:5432/produto'
ENTRYPOINT java -jar produtos.jar
