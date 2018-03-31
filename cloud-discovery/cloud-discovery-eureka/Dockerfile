FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ADD ./target/cloud-discovery-eureka-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/cloud-discovery-eureka-0.0.1.jar"]

EXPOSE 8761