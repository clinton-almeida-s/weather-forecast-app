# Use Maven for build
FROM maven:3.8.4-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Use JRE for runtime
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY public ./public
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
