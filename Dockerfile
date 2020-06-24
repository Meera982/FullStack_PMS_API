FROM maven:3.5.2-jdk-8-alpine AS BUILD_IMAGE
RUN rm -r /tmp/
COPY pom.xml /tmp/
COPY src /tmp/src/
COPY .git /tmp/.git/
WORKDIR /tmp/
RUN mvn -Pdev clean install package

FROM openjdk:8-jdk-alpine
COPY --from=BUILD_IMAGE /tmp/target/ProjectMangmentSystem*.jar ./ProjectMangmentSystem.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/ProjectMangmentSystem.jar"]
VOLUME /var/lib/pms/config-repo
EXPOSE 8085