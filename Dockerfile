FROM maven:3.8.5-openjdk-18 AS build
COPY post-service/src /home/app/post-service/src
COPY user-service/src /home/app/user-service/src
COPY pom.xml /home/app
COPY post-service/pom.xml /home/app/post-service/
COPY user-service/pom.xml /home/app/user-service/
RUN mvn -f /home/app/post-service/pom.xml clean package
RUN mvn -f /home/app/user-service/pom.xml clean package

#
# Package stage
#
FROM openjdk:18
COPY --from=build /home/app/post-service/target/post-service.jar /usr/local/lib/post-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/post-service.jar"]
FROM openjdk:18
COPY --from=build /home/app/user-service/target/user-service.jar /usr/local/lib/user-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/user-service.jar"]