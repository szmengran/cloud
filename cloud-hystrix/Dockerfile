FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_FILE cloud-hystrix-0.0.1.jar

#Set Beijing time zone
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone 

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200m -jar /app/$JAR_FILE

EXPOSE 10020