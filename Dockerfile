FROM java:8
VOLUME /tmp
COPY target/tra-spring-boot-conference-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]