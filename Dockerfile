FROM amazoncorretto:17-alpine-jdk
ADD target/fastfood-order-management-0.0.1.jar fastfood-order-management.jar
ENTRYPOINT ["java", "-jar", "fastfood-order-management.jar"]
EXPOSE 8080