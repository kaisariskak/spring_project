FROM eclipse-temurin:8-jre

ARG JAVA_OPTS
WORKDIR /opt/app

COPY app.jar /opt/app/app.jar

EXPOSE 50004

ENTRYPOINT ["/bin/sh","-c","exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/app/app.jar --spring.profiles.active=${SPRING_PROFILE}"]
