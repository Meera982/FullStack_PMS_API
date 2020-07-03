FROM jenkins/jenkins:latest

USER root
COPY docker /usr/bin/docker
RUN groupadd -g 975 docker &&
usermod -aG docker jenkins

USER jenkins