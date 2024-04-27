# install Linux https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository
# Docker desktop https://docs.docker.com/desktop/install/ubuntu/
# https://hub.docker.com/_/tomcat/tags
FROM tomcat:jdk17
# FROM tomcat:9.0.85-jdk17-corretto-al2
# FROM tomcat:9.0-jdk17-corretto-al2
# FROM tomcat:8.5.97-jdk17-corretto-al2
# https://hub.docker.com/layers/library/tomcat/7.0.109-jdk8-openjdk/images/sha256-489823486120d076cb576640c5819c6fa54948f470b46c54f02b48f462eb2c23

ARG APP_WAR_FILE_VERSION
ENV APP_WAR_FILE_VERSION=$APP_WAR_FILE_VERSION
ENV APP_WAR_FILE_NAME="AttendanceTrak"
ENV APP_WAR_FILE_OUTPUT=$APP_WAR_FILE_NAME-${APP_WAR_FILE_VERSION}
ENV APP_FULL_WAR_FILE_PATH="build/libs/$APP_WAR_FILE_OUTPUT"

LABEL org.nz.itlatinos.image.authors="aleonrangel@outlook.co.nz"
LABEL maintainer="andres.nz"
LABEL war.version="$APP_WAR_FILE_VERSION"
LABEL docker.image.build.command="docker build --build-arg APP_WAR_FILE_VERSION=$APP_WAR_FILE_VERSION --tag aleon1220/soa:latest ."

RUN printf "\t\t Docker Engine Build APP_WAR_FILE_VERSION %s" $APP_WAR_FILE_VERSION
RUN printf "\t\t Docker Engine Build APP_WAR_FILE_NAME $APP_WAR_FILE_NAME"
RUN printf "\t\t Docker Engine Build WAR APP_WAR_FILE_OUTPUT $APP_WAR_FILE_OUTPUT"
RUN printf "\t\t Docker Engine Build Path to war file $APP_FULL_WAR_FILE_PATH"

# RUN echo ${PWD} && ls -lR # Debugging
# USER 1000 # considered permissions issue
COPY ${APP_FULL_WAR_FILE_PATH}.war /usr/local/tomcat/webapps
# COPY build/libs/AttendanceApp-0.7.2 /usr/local/tomcat/webapps/
# https://forums.docker.com/t/docker-build-multistage-failed-to-compute-cache-key/134316/20