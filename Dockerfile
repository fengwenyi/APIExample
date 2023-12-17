FROM openjdk:17-jdk-alpine
MAINTAINER Erwin Feng xfsy_2015@163.com

ENV active = 'dev'

COPY build/libs/erwin-sample-*.jar /erwin-sample.jar
ENTRYPOINT ["sh", "-c", "java -jar erwin-sample.jar --spring.profiles.active=$active"]