# simple-java-maven-app (Spring Boot)

This repository is for the
[Build a Java app with Maven](https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/)
tutorial in the [Jenkins User Documentation](https://jenkins.io/doc/), now converted to Spring Boot.

The repository contains a simple Spring Boot application which outputs the string
"Hello world!" to the console and also exposes it via a REST endpoint at `/hello`.
The application is accompanied by unit tests and integration tests to check that both
the console output and REST endpoint work as expected. The results of these tests are saved to a
JUnit XML report.

## Running the Application

### As a Spring Boot application:
```bash
mvn spring-boot:run
```

### As a packaged JAR:
```bash
mvn clean package
java -jar target/my-app-1.0-SNAPSHOT.jar
```

### Testing the REST endpoint:
```bash
curl http://localhost:8080/hello
```

The `jenkins` directory contains an example of the `Jenkinsfile` (i.e. Pipeline)
you'll be creating yourself during the tutorial and the `jenkins/scripts` subdirectory
contains a shell script with commands that are executed when Jenkins processes
the "Deliver" stage of your Pipeline.
