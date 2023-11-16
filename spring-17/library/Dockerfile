FROM openjdk:17-jdk-slim

ENV VERTICLE_HOME /usr/app

ARG CACHEBUST=1

COPY target/library-jar-with-dependencies.jar $VERTICLE_HOME/library.jar

ARG JAVA_OPTS=""
WORKDIR $VERTICLE_HOME

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ./library.jar"]