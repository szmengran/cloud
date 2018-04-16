FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_VERSION configserver.jar
ADD ./target/$JAR_VERSION /app/
CMD ["java", "-Xmx200m", "-jar", "/app/$JAR_VERSION"]

EXPOSE 10001