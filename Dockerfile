FROM amazoncorretto:17
WORKDIR /app
COPY build/libs/DBMS.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]







