FROM openjdk:8-jre-alpine
MAINTAINER Joe<android_li@sina.cn>

ENV JAR_FILE cloud-suntak-reserve.jar

RUN echo 'Asia/shanghai' > /etc/timezone

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200 -jar /app/$JAR_FILE

EXPOSE 10023