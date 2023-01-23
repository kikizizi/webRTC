FROM ubuntu:18.04

ENV DEBIAN_FRONTEND=noninteractive \ 
    LANG=C.UTF-8

RUN apt-get update && apt-get install -y openjdk-8-jdk

COPY ./signaling /opt/signaling

WORKDIR /opt/signaling

RUN ./gradlew --refresh-dependencies

