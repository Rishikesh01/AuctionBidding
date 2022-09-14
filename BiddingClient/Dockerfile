FROM alpine:3.16.2

RUN  apk update \
  && apk upgrade \
  && apk add --update openjdk11 tzdata curl unzip bash \
  && rm -rf /var/cache/apk/

ARG JAR_FILE=build/libs/BiddingClient.jar
COPY ${JAR_FILE} BidClient.jar
