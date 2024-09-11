FROM openjdk:11
LABEL org.opencontainers.image.authors="plumsun@163.com"
VOLUME /tmp
ADD short_chains.jar /tmp/
WORKDIR /tmp/
ENTRYPOINT ["java","-jar","short_chains.jar"]
EXPOSE 8090