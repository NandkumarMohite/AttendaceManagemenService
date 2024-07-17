FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/attendence-application-jar.jar attendence-application-jar.jar
EXPOSE 8080
CMD ["java","-jar","attendence-application-jar.jar"]