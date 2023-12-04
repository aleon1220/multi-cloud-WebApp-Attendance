# Maven Builds
---
### Maven Stages
if you still want to build with maven
```bash
mvn package
```

- build the project locally with a locally installed maven client

```bash
mvn verify
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
---