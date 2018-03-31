FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ADD ./target/cloud-hystrix-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/cloud-hystrix-0.0.1.jar"]

EXPOSE 8888