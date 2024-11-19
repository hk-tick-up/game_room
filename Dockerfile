FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*T.jar tickup.jar

CMD ["java", "-jar", "tickup.jar"]