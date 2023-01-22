FROM openjdk:17-alpine
MAINTAINER dpimkin.it@gmail.com
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser:appuser
ARG JAR_FILE
ADD ${JAR_FILE} /app/location-tracker.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=standalone-redis","-jar","/app/location-tracker.jar"]