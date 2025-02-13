- [Attendance WebApp](#attendance-webapp)
  - [Introduction](#introduction)
  - [GitHub Reports](#github-reports)
    - [Security warnings](#security-warnings)
    - [GitHub Vulnerability report](#github-vulnerability-report)
  - [Attendance WebApp Architecture (re-architected)](#attendance-webapp-architecture-re-architected)
    - [2018-Attendance WebApp high level Architecture](#2018-attendance-webapp-high-level-architecture)
    - [Attendance WebApp UI](#attendance-webapp-ui)
  - [Refer to the Wiki for details on the project](#refer-to-the-wiki-for-details-on-the-project)
- [Quickstart](#quickstart)
- [Local Setup](#local-setup)
  - [Project general guidelines](#project-general-guidelines)
    - [Maven Build](#maven-build)
    - [Perform local testing](#perform-local-testing)
    - [Gradle Test suite](#gradle-test-suite)
      - [Use 1Password CLI to inject the secrets](#use-1password-cli-to-inject-the-secrets)
      - [Execute the Gradle commands](#execute-the-gradle-commands)
      - [Set the variables](#set-the-variables)
      - [Docker Image Build](#docker-image-build)
      - [Available Tomcat versions](#available-tomcat-versions)
    - [Execute WebApp Container Execution](#execute-webapp-container-execution)
      - [Docker execution by image version](#docker-execution-by-image-version)
      - [Docker-compose](#docker-compose)
  - [Editing project diagrams](#editing-project-diagrams)
- [References](#references)

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

![Attendance App Architecture](https://private-user-images.githubusercontent.com/2084646/294421150-a020470e-9154-4528-a57f-ed7b1b2bc405.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDQ1MjgzNDMsIm5iZiI6MTcwNDUyODA0MywicGF0aCI6Ii8yMDg0NjQ2LzI5NDQyMTE1MC1hMDIwNDcwZS05MTU0LTQ1MjgtYTU3Zi1lZDdiMWIyYmM0MDUucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI0MDEwNiUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNDAxMDZUMDgwMDQzWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9NDhlMDc0NWY4MDIzZTc0ZjVkNDRiOGVmMGNmYTkzMDZhZWJjMjdkNTI4OTM3ODA2NGJhNDNkODZiM2E2MzdkYiZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.26wUMpUxwC2owmY3r7GPfItCZZmBVPp5OfpPMtgoFNk)

### Attendance WebApp UI

![Attendance Web App](https://github.com/aleon1220/multi-cloud-AttendWebApp/wiki/images/2018/05/random-code-generation.png)

## Refer to the Wiki for details on the project
[Detailed Project Wiki](https://github.com/aleon1220/multi-cloud-WebApp-Attendance/wiki/2018%E2%80%9001-Introduction)

# Quickstart
- Gradle Build Web Package
build and test the WebArchive file. Generates the .WAR file in `build/libs/*.war`
``` bash
gradle clean build --console plain --warning-mode all
```
- Gradle Check the generated version from the build.gradle.kts
```bash
gradle getAppversion
```
- Run WebApp
Run the tomcat server with the latest pre-built WAR web Archive file
Run from [Docker Hub](https://hub.docker.com/repository/docker/aleon1220/soa/general)
```bash
docker run -itd --publish 8080:8080 --name attendance_webapp_container aleon1220/soa:latest
```

# Local Setup
> Tested in Win11 with WSL, Github codespaces and Linux Ubuntu 22

## Project general guidelines

1. Installation process: project a Java project JEE. Import in any IDE and build with gradle or maven. Build docker image and run
2. Software dependencies: dependencies are described in pom.xml file
3. Latest releases by using git tags
4. API references. API docs
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

### Maven Build
> maven has been deprecated and moved to [maven](./maven)

### Perform local testing
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
#### Execute the Gradle commands
Should do validations
```bash
gradle check --warning-mode all
```
#### Set the variables

#### Docker Image Build
- Build the app image with Docker. Deploy .WAR file in Tomcat
refer to https://hub.docker.com/_/tomcat
```bash
docker build --build-arg APP_WAR_FILE_VERSION=$APP_WAR_FILE_VERSION --tag aleon1220/soa:$APP_WAR_FILE_VERSION .
```

#### Available Tomcat versions
if you need to edit the Dockerfile and upgrade the servlet container Tomcat version
- 7.0.109 = `TOMCAT_VERSION_DOCKER_TAG="7.0.109-jdk8-openjdk"`
- 9.0.78  = `TOMCAT_VERSION_DOCKER_TAG="9.0.78-jre8"`

> for particular versions check the image in Docker hub

### Execute WebApp Container Execution
#### Docker execution by image version
Test the immutable webapp from Docker
```bash
docker run -itd --publish 8080:8080 aleon1220/soa:$APP_WAR_FILE_VERSION
```

##### local development & executions
Test the container webapp after building the image locally
```bash
docker run -itd --publish 8080:8080 --name attendance_webapp_container aleon1220/soa:$APP_WAR_FILE_VERSION
```
- Get the name of the running container
``` bash
CONTAINER_NAME=$(docker container ls --all --filter publish=8080 --format "{{.Names}}")
```
- The URl is hostname:8080/$CONTEXT
- Get the context of the webapp
```bash
TOMCAT_URL="http://$(hostname):8080/Attendance-$APP_WAR_FILE_VERSION"
```
- Access the Docker container via CLI
```bash
docker container exec -it $CONTAINER_NAME /bin/bash
```
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

## Git Diagram
```mermaid
gitGraph
   branch master
   commit id: "2017 init Commit"

    branch develop
    commit id: "develop"   
    checkout master

   branch feature/sot-workshop
   commit id: "School of tech"
   checkout master

   branch hotfix/master-gitHubActions
   commit id: "add gitHub Actions"
   checkout master

   branch feature/login-LDAP
   commit id: "test new functionality"
   checkout develop
   
   branch hotfix/stable-build-latest-primefaces-2024
   commit id: "Latest UI framework"
   checkout develop
```

# References
- [free website templates](http://all-free-download.com/free-website-templates)
