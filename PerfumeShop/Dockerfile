FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean package
EXPOSE 8080
CMD ["java", "-jar", "target/perfume-shop-0.0.1-SNAPSHOT.jar"]