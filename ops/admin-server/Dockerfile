FROM openjdk:8u201-jre-alpine3.9
RUN addgroup -S app && adduser -SDG app app
WORKDIR /home/app
RUN mkdir -p /home/app/log
VOLUME /tmp
ARG PROJECT_NAME
ARG JAR_FILE
ADD target/$JAR_FILE ./app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./app.jar
EXPOSE 8090