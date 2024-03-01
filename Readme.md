# VibenChat
WEB for chilling with people and listening music together

### Prerequisites
This project used Bootstrap 5 and Spring Boot 3.2.1.
Spring Security version 6.2 included.

### Requirements
* Language JDK 17
* Check if JAVA_HOME used JDK 17

## Run DB with Docker
### Run mysql
```
docker compose up -d mysqldb
```

## Run application using spring-boot
### on H2 DB
```
./mvnw spring-boot:run
```

### on mysql DB
```
./mvnw spring-boot:run -Dspring.profiles.active=mysql
```

## Access the application
http://localhost:8080