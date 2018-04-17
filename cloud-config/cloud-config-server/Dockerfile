FROM java:8-jre
MAINTAINER Joe <android_li@sina.cn>

ENV JAR_FILE cloud-config-server-1.5.10.RELEASE.jar

ADD ./target/$JAR_FILE /app/
CMD java -Xmx200m -jar /app/$JAR_FILE

EXPOSE 10001