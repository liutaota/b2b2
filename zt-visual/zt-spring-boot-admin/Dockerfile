FROM openjdk:8-jre-slim
MAINTAINER peiqy@foxmail.com

ENV PARAMS=""

ENV TZ=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD target/zt-spring-boot-admin-*.jar /app-02.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app-02.jar $PARAMS"]