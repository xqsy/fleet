FROM eclipse-temurin:21-jdk-alpine-3.22 AS build
WORKDIR /workspace

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY driver-workload-spring-boot-starter ./driver-workload-spring-boot-starter
COPY trip-cost-service ./trip-cost-service
RUN chmod +x gradlew
RUN ./gradlew :trip-cost-service:dependencies --no-daemon
COPY trip-cost-service/src ./trip-cost-service/src
RUN ./gradlew :trip-cost-service:bootJar --no-daemon


FROM eclipse-temurin:21-jre-alpine-3.22
WORKDIR /app
COPY --from=build /workspace/trip-cost-service/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]