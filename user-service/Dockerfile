#
# Package stage
#
FROM openjdk:18
COPY target/user-service.jar /usr/local/lib/user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/usr/local/lib/user-service.jar"]