FROM java:8

MAINTAINER www.weixuan@163.com

VOLUME /tmp

#Copy a prebuilt java application to the container
ADD target/dingding-v1.jar dingding-v1.jar

RUN bash -c 'touch /dingding-v1.jar'

# Expose HTTP port 8080 for tomcat triggered by Spring Boot
EXPOSE 8000

# bring the application up and running.
ENTRYPOINT ["java","-jar","/dingding-v1.jar"]
