FROM openjdk:17
EXPOSE 9001
ADD target/spring-redis-demo.jar spring-redis-demo.jar
ENTRYPOINT ["java" "-jar" "spring-redis-demo.jar"]