FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_VERSION cloud-common-sms-0.0.1.jar
ADD ./target/$JAR_VERSION /app/
CMD ["java", "-Xmx200m", "-jar", "/app/$JAR_VERSION"]

EXPOSE 10004