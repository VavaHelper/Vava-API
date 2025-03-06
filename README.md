<h1 align="center"> Spring Boot API </h1>

[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#table-of-contents)

## Descrição
Este projeto é uma API de autenticação desenvolvida com **Spring Boot** e **Spring Security**, utilizando **MariaDB** como banco de dados. A API fornece funcionalidades de **registro** e **login** de usuários e será consumida por um frontend desenvolvido em outro framework.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **MariaDB**
- **JWT (JSON Web Token)**
- **Maven**

## Requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- **Java 17** ou superior
- **MariaDB** instalado e rodando
- **Maven** configurado

## Configuração do Banco de Dados
O projeto está configurado para utilizar **MariaDB**. Para configurá-lo, crie um banco de dados e edite o arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect
```

## Como Rodar o Projeto
1. Clone o repositório:
   ```sh
   git clone https://github.com/VavaHelper/Vava-API.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd Vava-API
   ```
3. Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

## Como Rodar o Projeto (DSV)
1. Clone o repositório:
   ```sh
   git clone https://github.com/VavaHelper/Vava-API.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd Vava-API
   ```
3. Acesse a branch DSV:
   ```sh
   git checkout development
   ```
4. Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

## Endpoints Disponíveis
A API oferece os seguintes endpoints:

### Registro de Usuário
- **POST** `/api/auth/register`
- **Request Body:**
  ```json
  {
    "username": "exemplo",
    "email": "exemplo@email.com",
    "password": "senha123"
  }
  ```

### Login de Usuário
- **POST** `/api/auth/login`
- **Request Body:**
  ```json
  {
    "email": "exemplo@email.com",
    "password": "senha123"
  }
  ```
- **Resposta (sucesso):**
  ```json
  {
    "token": "jwt_token_aqui"
  }
  ```

## Futuras Implementações
- Recuperação de senha
- Perfis de usuários (Admin, Usuário Comum)
- Integração com OAuth2
- Atendimento/Suporte ao Usuário Cliente


## 📚 Documentações importantes
Caso esteja com dúvidas, verifique algumas das documentações abaixo, podem ser úteis:
- [Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/) - Saiba como padronizar seus commits do GitHub, isso será muito importante para a organização do projeto;  
- [Java Spring Boot](https://spring.io/projects/spring-boot) - Framework para criação de aplicações Java, facilitando a configuração e o desenvolvimento;  
- [Spring Security](https://spring.io/projects/spring-security) - Framework para autenticação e controle de acesso em aplicações Spring Boot;  
- [Java - Sintaxe](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/index.html) - Guia oficial da Oracle sobre a sintaxe básica da linguagem Java;  
- [Design Patterns em Java](https://refactoring.guru/design-patterns/java) - Padrões de projeto explicados com exemplos práticos em Java.  

Se tiver dúvidas sobre como iniciar ou progredir, converse com o resto da equipe!

## Licença
Este projeto está sob a licença MIT. Sinta-se à vontade para utilizá-lo e modificá-lo.


