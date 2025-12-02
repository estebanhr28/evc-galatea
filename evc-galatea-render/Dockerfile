# Etapa 1: Build usando Maven y JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn -q -e -DskipTests clean package

# Etapa 2: Imagen final liviana
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/evc-galatea-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
