FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ADD ./target/cloud-security-oauth-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/cloud-security-oauth-0.0.1.jar"]

EXPOSE 9999