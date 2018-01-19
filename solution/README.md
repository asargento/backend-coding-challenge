# backend_challenge

A simple backend to complement the supplied frontend code.

Prerequisites
---

Before start, is necessary to have the following components installed:

- [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
- [Maven](http://maven.apache.org)


How to start the backend_challenge application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/backend-challenge-1.0.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8085`

Running the tests
---

For running the tests run `mvn tests`.

Built with
---

- [Dropwizard](http://http://www.dropwizard.io), for developing the RESTful API
- [Maven](http://maven.apache.org), for building everything
- [Hibernate](http://hibernate.org), for mapping objects into a database
- [H2](http://www.h2database.com), the database for storing the data
- [JUnit 4](http://junit.org/junit4/), for testing 

Configuration
---

The application is configured by a file in [YAML](http://www.yaml.org) format and the majority of the properties defined on that file are Dropwizard configuration (can find the properties specification [here](http://www.dropwizard.io/1.2.2/docs/manual/configuration.html)).
Two properties could be defined on this file for configure the application:

- **dateFormat**: specifies the date format used on the application (example: dd/MM/yyyy)
- **vatTax**: specified the decimal value for VAT (example: 0.20, for 20% VAT)