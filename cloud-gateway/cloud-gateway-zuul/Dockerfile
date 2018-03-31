FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ADD ./target/cloud-gateway-zuul-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/cloud-gateway-zuul-0.0.1.jar"]

EXPOSE 10000