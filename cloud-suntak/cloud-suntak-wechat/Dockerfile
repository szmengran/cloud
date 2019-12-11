FROM openjdk:8-jre-alpine
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_FILE cloud-suntak-wechat.jar

#Set Beijing time zone
RUN echo 'Asia/Shanghai' >/etc/timezone 

ADD ./target/$JAR_FILE /app/
CMD java -Xms50m -Xmx300m -jar /app/$JAR_FILE

EXPOSE 10014