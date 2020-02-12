FROM openjdk:8
ADD target/docker-api-exchange.jar docker-api-exchange.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar","docker-api-exchange.jar"]