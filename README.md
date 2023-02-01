# Attendance WebApp

![Attendance Web App](https://github.com/aleon1220/multi-cloud-AttendWebApp/wiki/images/2018/05/random-code-generation.png)

_Attendance Screen_

![Multi Cloud architecture](https://imgur.com/LDVqx71.jpg)

_General View of Multi Cloud interoperability scenario_

# Introduction
Attendance WebApp is a proof of concept to improve the way attendance is managed at AUT university. the application uses maven, J2EE, primefaces, gson.
The idea is that you have a short timeframe to submit a random generated code by the lecturer so that attendance can be registered in the system.


The application is a proof of concept for Service orientation and Service interoperability in the cloud.

## Refer to the Wiki for details on the project
[Detailed Project Wiki][95f44386]

  [95f44386]: https://github.com/aleon1220/multi-cloud-AttendWebApp/wiki/4-Architecture-and-Technical-Design "Project Wiki"

# Getting Started
1.	Installation process: project is Maven java project. Import in any IDE with the POM File.
2.	Software dependencies: dependencies are described in pom.xml file
3.	Latest releases: this is going to be first reference
4.	API references

# Build and Test
git clone repo
import in IDE Eclipse suggested
execute some of the unit tests
mvn package will generate the .WAR file
deploy .WAR in tomcat.
## Run the maven build Locally
- build the project locally with a locally installed maven client
> Tested in Win11 with WSL
``` bash
mvn verify
```
- Use a docker tag to build the image
> 8-jdk8-corretto

``` bash
TOMCAT_DOCKER_TAG="8-jdk8-corretto"
```
- build the container to the latest version tag
``` bash
docker build --tag aleon1220/soa:latest
```

- Run the tomcat server with the pre-built WAR web Archive file
Use the tag latest or a particular version e.g. v2
```
docker run -itd --publish 8888:8080 --name attendance_webapp_container aleon1220/soa
```

refer to https://hub.docker.com/_/tomcat

- Access container
``` bash
docker container exec -it aleon1220/soa /bin/bash
```

- The URl is localhost:8888/AttendanceWebApp 
[AttendanceWebApp](localhost:8888/AttendanceWebApp)

---
## Build using docker containers
Refer to maven docker official image https://hub.docker.com/_/maven 

- Create a volume
``` bash
docker volume create --name maven-repo-volume
```

- Docker container build using the volume above
``` bash
docker run -it -v maven-repo-volume:/root/.m2 maven mvn archetype:generate # will download artifacts
```

- Docker container run and build using the maven image
``` bash
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install
```

- docker run build using bind volume mount
``` bash
docker run -it --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install
```

# Contribute
* Create an automated YML automated deployment configuration to test the multi-cloud deployment
* Cloud environment using docker containers and execute a performance testing
* update visual looks and improve ui and UX of the apps

# Future Versions
* include video tutorial on functionality and relate to GitHub repos containing the cloud source code
* replicate the cloud services using containers orchestration
* create a composite service by uniting functionalities of the different platforms.

## GitHub Vulnerability report
https://github.com/aleon1220/multi-cloud-WebApp-Attendance/security/dependabot