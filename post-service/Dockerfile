#
# Package stage
#
FROM openjdk:18
COPY target/post-service.jar /usr/local/lib/post-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/post-service.jar"]