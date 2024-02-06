FROM amazoncorretto:17-al2-jdk

LABEL authors="mrcinkowski"

COPY ./target/ShoppingApp-0.0.1-SNAPSHOT.jar ShoppingApp-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/ShoppingApp-0.0.1-SNAPSHOT.jar"]