- [Attendance WebApp](#attendance-webapp)
  - [Introduction](#introduction)
  - [Attendance WebApp Architecture (re-architected)](#attendance-webapp-architecture-re-architected)
    - [2018-Attendance WebApp high level Architecture](#2018-attendance-webapp-high-level-architecture)
    - [Attendance WebApp UI](#attendance-webapp-ui)
  - [Refer to the Wiki for details on the project](#refer-to-the-wiki-for-details-on-the-project)
- [Project general guidelines](#project-general-guidelines)
  - [Locally Build and Test](#locally-build-and-test)
  - [Maven Stages](#maven-stages)
    - [Run the maven build Locally](#run-the-maven-build-locally)
  - [Docker](#docker)
    - [Run and test the container webapp](#run-and-test-the-container-webapp)
    - [Build using maven docker container](#build-using-maven-docker-container)
  - [GitHub Vulnerability report](#github-vulnerability-report)

# Attendance WebApp

## Introduction

Attendance WebApp is a proof of concept to improve the way attendance is managed at AUT university.

The application uses maven, J2EE, primefaces, gson.
The idea is that you have a short timeframe to submit a random generated code by the lecturer so that attendance can be registered in the system.

The application is a proof of concept for Service orientation and Service interoperability in the cloud

> Security Warning
GitHub found 2 vulnerabilities on aleon1220/multi-cloud-WebApp-Attendance's default branch (2 moderate).
To find out more, visit:
[This project security report](https://github.com/aleon1220/multi-cloud-WebApp-Attendance/security)

## Attendance WebApp Architecture (re-architected)

2018 project with a lot of things to change

* Simplification of used services
* Streamline app to use 100% rest and deprecate SOAP and WSDL
* Create a simple test OpenLDAP instance for users and roles
* Host the WebApp in Azure
* Host the backend functionality in AWS
* use diagrams.net for the diagrams

### 2018-Attendance WebApp high level Architecture

![Multi Cloud architecture](https://imgur.com/LDVqx71.jpg)

### Attendance WebApp UI

![Attendance Web App](https://github.com/aleon1220/multi-cloud-AttendWebApp/wiki/images/2018/05/random-code-generation.png)

## Refer to the Wiki for details on the project

[Detailed Project Wiki][95f44386]

  [95f44386]: https://github.com/aleon1220/multi-cloud-AttendWebApp/wiki/4-Architecture-and-Technical-Design "Project Wiki"

# Project general guidelines

1. Installation process: project is Maven java project. Import in any IDE with the POM File.
2. Software dependencies: dependencies are described in pom.xml file
3. Latest releases by using git tags
4. API references. API docs

## Locally Build and Test
> Tested in Win11 with WSL
- Clone git repo
```bash
git clone repo
```

- open in IDE Eclipse, IntelliJ (suggested) or use online IDE (Github codespaces)

- Pack the WebArchive file using maven. Generate the .WAR file
``` bash
mvn package
```

- Build the app image with Docker. Deploy .WAR file in Tomcat
refer to https://hub.docker.com/_/tomcat
```bash
TOMCAT_DOCKER_TAG="8-jdk8-corretto"
docker build --tag aleon1220/soa:latest .
```

- Run the tomcat server with the pre-built WAR web Archive file
  Use the tag latest or a particular version e.g. aleon1220/soa:v2 or aleon1220/soa:latest

```bash
docker run -itd --publish 8888:8080 aleon1220/soa:latest
```
- get the name of the running container in port 8888
``` bash
CONTAINER_NAME=$(docker container ls --all --filter publish=8888 --format "{{.Names}}")
```

- Access the Docker container via CLI

```bash
docker container exec -it $CONTAINER_NAME /bin/bash
```

- The URl is URL:8888/AttendanceWebApp [AttendanceWebApp](http://localhost:8888/AttendanceWebApp)


- clean up docker container environment
``` bash
docker stop $(docker ps --quiet)
docker rm $(docker container ls --all --quiet)
```
## Maven Stages

### Run the maven build Locally

- build the project locally with a locally installed maven client

```bash
mvn verify
```
---

## Docker
### Run and test the container webapp
```bash
docker build --tag aleon1220/soa:latest .
docker run -itd --publish 8888:8080 --name attendance_webapp_container aleon1220/soa:latest
```
### Build using maven docker container

Refer to maven docker official image https://hub.docker.com/_/maven
is best to have maven locally installed

- Create a volume

```bash
docker volume create --name maven-repo-volume
```

- Docker container build using the volume above

```bash
docker run -it -v maven-repo-volume:/root/.m2 maven mvn archetype:generate # will download artifacts
```

- Docker container run and build using the maven image

```bash
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install
```

- docker run build using bind volume mount

```bash
docker run -it --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install
```

## GitHub Vulnerability report

https://github.com/aleon1220/multi-cloud-WebApp-Attendance/security/dependabot
