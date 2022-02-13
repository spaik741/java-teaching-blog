### BUILD ###
FROM maven:3.6-jdk-11-slim as maven
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY pom.xml $PROJECT_DIR

RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src $PROJECT_DIR/src
RUN mvn package -Dmaven.test.skip=true

### RUN ###
FROM openjdk:11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=maven  $PROJECT_DIR/src   $PROJECT_DIR/src
COPY --from=maven  $PROJECT_DIR/target  $PROJECT_DIR/target
COPY --from=maven $PROJECT_DIR/target/*.jar $PROJECT_DIR/app.jar

# Add docker-compose-wait tool -------------------
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/opt/project/app.jar"]
CMD ["java","-Xms400m","-Xmx400m", "-jar", "/opt/project/app.jar"]
