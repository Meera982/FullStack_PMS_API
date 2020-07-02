FROM adoptopenjdk/openjdk11:jdk-11.0.2.9
WORKDIR /App
ADD target/ProjectMangmentSystem*.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar