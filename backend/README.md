# Projeto Housekeeper

Projeto da imersão Multi Stack [TreinaWeb](http://treinaweb.com.br/), desenvolvido modificando conteúdo para fim de aprendizado, utilizando Java e Spring Boot. 

## Dependências do Projeto

- Spring Boot
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- Bean Validation

## Dependências de Desenvolvimento

- Spring Boot Devtools
- Lombok

## Requisitos

- Java 17
- Maven 3.8

## Como testar esse projeto na minha máquina?

Clone este repositório e entre na pasta do projeto.

```sh
git clone https://github.com/HumbertoLuiz/housekeeper-spring.git
cd housekeeper-spring
```

Atualize as configurações de acesso ao banco de dados no arquivo [application.properties](src/main/resources/application.properties).

```properties
spring.datasource.url=jdbc:mysql://host:porta/banco_de_dados
spring.datasource.username=usuario
spring.datasource.password=senha
```

Execute o projeto através do Maven.

```sh
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/jobs](http://localhost:8080/admin/jobs).