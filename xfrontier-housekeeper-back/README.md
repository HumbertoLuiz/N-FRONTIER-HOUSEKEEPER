# Housekeeper Project

This project is from the [TreinaWeb](http://treinaweb.com.br/) Multi Stack immersion course, developed by modifying content for learning purposes, using Java and Spring Boot.


## Project Dependencies

- JDK 23
- Spring Boot
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- Bean Validation

## Development Dependencies

- Spring Boot Devtools
- Lombok

## requirements

- Java 17
- Maven 3.8

## How to test this project on my computer?

Clone this repository and enter the folder project.

```sh
git clone https://github.com/HumbertoLuiz/housekeeper-spring.git
cd housekeeper-spring
```

Update Access configurations data base file [application.properties](src/main/resources/application.properties).

```properties
spring.datasource.url=jdbc:mysql://host:port/data_base_name
spring.datasource.username=your_user
spring.datasource.password=your_password
```

run through Maven.

```sh
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/jobs](http://localhost:8080/admin/jobs).
