FROM open-liberty:microProfile3
USER root
MAINTAINER IBM Java engineering at IBM Cloud
COPY /target/liberty/wlp/usr/servers/demo-servive-a /config/

RUN apt-get update -y && apt-get install -y curl
USER 1001
EXPOSE 9080 9443
