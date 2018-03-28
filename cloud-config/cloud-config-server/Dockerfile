FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ADD ./target/cloud-config-server-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/cloud-config-server-0.0.1.jar"]

EXPOSE 4000