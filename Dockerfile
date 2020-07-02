FROM frolvlad/alpine-oraclejdk8:slim
ADD target/ProjectMangmentSystem*.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar