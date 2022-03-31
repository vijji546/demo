# Spring Boot "Player Microservice" Project

This is a sample Java / Maven / Spring Boot application that can be used to retrieve list of players

## How to Run
* Clone this repository
* Make sure you are using JDK 1.8 
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service using mvn spring-boot:run

Once the application runs you should see something like this

2022-03-31 15:25:03.992  INFO 12612 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-03-31 15:25:04.013  INFO 12612 --- [           main] com.player.demo.PlayerDemoApplication    : Started PlayerDemoApplication in 5.381 seconds (JVM running for 6.318)

## About the Service
It is simple REST service with one endpoint GET /api/players to return the list of players by parsing a file called player.csv


