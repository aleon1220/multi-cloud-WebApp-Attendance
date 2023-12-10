- [Attendance WebApp](#attendance-webapp)
  - [Introduction](#introduction)
  - [GitHub Reports](#github-reports)
    - [Security warnings](#security-warnings)
    - [GitHub Vulnerability report](#github-vulnerability-report)
  - [Attendance WebApp Architecture (re-architected)](#attendance-webapp-architecture-re-architected)
    - [2018-Attendance WebApp high level Architecture](#2018-attendance-webapp-high-level-architecture)
    - [Attendance WebApp UI](#attendance-webapp-ui)
  - [Refer to the Wiki for details on the project](#refer-to-the-wiki-for-details-on-the-project)
- [Project general guidelines](#project-general-guidelines)
- [Setup](#setup)
  - [Build WebApp](#build-webapp)
    - [Gradle Build Web Package](#gradle-build-web-package)
    - [Maven Build](#maven-build)
    - [Gradle Test suite](#gradle-test-suite)
      - [Use 1Password CLI to inject the secrets](#use-1password-cli-to-inject-the-secrets)
  - [Package/Run WebApp](#packagerun-webapp)
    - [Docker Image Build](#docker-image-build)
      - [Available Tomcat versions](#available-tomcat-versions)
  - [Execute WebApp](#execute-webapp)
    - [Container Execution](#container-execution)
      - [Docker execution](#docker-execution)
      - [Docker-compose](#docker-compose)
  - [Editing project diagrams](#editing-project-diagrams)

# Attendance WebApp

## Introduction

Attendance WebApp is a proof of concept to improve the way attendance is managed at AUT university.

The application uses maven, J2EE, primefaces, gson.
```kotlin
    maven {
         url = uri("https://repository.primefaces.org")
    }
```
The idea is that you have a short timeframe to submit a random generated code by the lecturer so that attendance can be registered in the system.

The application is a proof of concept for Service orientation and Service interoperability in the cloud

## GitHub Reports
### Security warnings
> Security Warnings to check
GitHub found 2 vulnerabilities on aleon1220/multi-cloud-WebApp-Attendance's default branch (2 moderate).
To find out more, visit:
[This project security report](https://github.com/aleon1220/multi-cloud-WebApp-Attendance/security)
### GitHub Vulnerability report
https://github.com/aleon1220/multi-cloud-WebApp-Attendance/security/dependabot

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

1. Installation process: project a Java project JEE. Import in any IDE and build with gradle or maven. Build docker image and run
2. Software dependencies: dependencies are described in pom.xml file
3. Latest releases by using git tags
4. API references. API docs

# Setup
> Tested in Win11 with WSL, Github codespaces and Linux Ubuntu 22

- Clone repo HTTPS
```bash
git clone https://github.com/aleon1220/multi-cloud-WebApp-Attendance.git
```
- Clone Repo SSH
```bash
git clone git@github.com:aleon1220/multi-cloud-WebApp-Attendance.git
```
- open repo in chosen IDE
IDEs can be Eclipse, IntelliJ (suggested) or use online IDE (Github codespaces)

## Build WebApp
### Gradle Build Web Package
- Pack the WebArchive file. Generate the .WAR file
``` bash
gradle clean build --console plain --warning-mode all
```

### Maven Build
> maven has been deprecated and moved to [maven](./maven)

### Gradle Test suite
#### Use 1Password CLI to inject the secrets
- as a pre-requisite you must have access to the shared vault
- login in the CLI
```bash
# Linux Ubuntu tested 2023-12-10
op signin
```
- inject the secrets for testing Authentication property file
```bash
op inject -i token_auth.properties.tpl -o token_auth.properties
```

- inject the secrets for Testing property file
```bash
op inject -i secrets.env.tpl -o secrets.env
```

- inject the secrets for Testing docker-compose
```bash
op inject -i .env.tpl -o .env
```

## Package/Run WebApp
### Docker Image Build
- Build the app image with Docker. Deploy .WAR file in Tomcat
refer to https://hub.docker.com/_/tomcat
```bash
docker build --tag aleon1220/soa:latest .
```

#### Available Tomcat versions
Use the tag latest or a particular version e.g. aleon1220/soa:v2 or aleon1220/soa:latest
- 7.0.109 = `TOMCAT_VERSION_DOCKER_TAG="7.0.109-jdk8-openjdk"`
- 9.0.78  = `TOMCAT_VERSION_DOCKER_TAG="9.0.78-jre8"`

## Execute WebApp
### Container Execution
#### Docker execution

##### Run from [Docker Hub](https://hub.docker.com/repository/docker/aleon1220/soa/general)
Test the immutable webapp from Docker
```bash
docker run -itd --publish 8080:8080 --name attendance_webapp_container aleon1220/soa:latest
```

##### local build & Run 
Test the container webapp after building the image locally
```bash
docker build --tag aleon1220/soa:latest .
docker run -itd --publish 8080:8080 --name attendance_webapp_container aleon1220/soa:latest
```
Run the tomcat server with the pre-built WAR web Archive file
```bash
docker run -itd --publish 8080:8080 aleon1220/soa:latest
```
- get the name of the running container
``` bash
CONTAINER_NAME=$(docker container ls --all --filter publish=8080 --format "{{.Names}}")
```
- Access the Docker container via CLI
```bash
docker container exec -it $CONTAINER_NAME /bin/bash
```
- The URl is URL:8080/Attendance-0.0.1 [AttendanceWebApp](http://localhost:8080/AttendanceWebApp)
- clean up docker container environment
``` bash
docker stop $(docker ps --quiet)
docker rm $(docker container ls --all --quiet)
```

#### Docker-compose
- inject the secrets for Testing docker-compose
```bash
op inject -i .env.tpl -o .env
```
- single variable
```bash
export LDAP_ADMIN_PASS=$(op read "op://uqbpxejq7gifvi6mg3c7xxokre/jvuj7juvlxlg7delckucvidqhi/password")
```

## Editing project diagrams
- go to [diagrams.net](https://app.diagrams.net/?src=about)
- open the file [project-diagrams.drawio](./project-diagrams.drawio) XML file with the diagrams
- Explore > export images to convinience and update this README
