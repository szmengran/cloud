FROM openjdk:8-jre-alpine
MAINTAINER Joe <anroid_li@sina.cn>

ENV JAR_FILE cloud-suntak-erp.jar

RUN echo 'Asia/Shanghai' > /etc/timezone

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200m -jar /app/$JAR_FILE

EXPOSE 10025