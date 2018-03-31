FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ADD ./target/configserver.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/configserver.jar"]

EXPOSE 8888