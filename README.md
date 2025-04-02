# 🚀 Spring Boot API (Branch: Development)

## 📌 Descrição
Este projeto é uma API de **autenticação** desenvolvida com **Spring Boot** e **Spring Security**, utilizando **H2** como banco de dados em ambiente de desenvolvimento (**DSV**). A API fornece funcionalidades de **registro** e **login** de usuários e será consumida por um frontend desenvolvido em outro framework.

**📌 Esta documentação refere-se à branch `development` do projeto.**

## 🛠️ Tecnologias Utilizadas
- ☕ **Java 17**
- 🌱 **Spring Boot 3**
- 🔐 **Spring Security**
- 🗄️ **Spring Data JPA**
- 🛢️ **H2 Database** (Apenas para DSV)
- 🔑 **JWT (JSON Web Token)**
- 🏗️ **Maven**

## 📋 Requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- ✅ **Java 17** ou superior
- ✅ **Maven** configurado

## 🛢️ Configuração do Banco de Dados no Ambiente DSV
No ambiente de desenvolvimento (**DSV**), utilizamos o banco de dados **H2** em memória. Ele é um banco leve, embutido e ideal para testes. O `application.properties` está configurado da seguinte forma:

```properties
# Configuração do banco de dados para o ambiente de desenvolvimento (DSV)
spring.datasource.url=jdbc:h2:mem:apidsv
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=123

# Configuração do Hibernate (JPA)
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Habilitar o console web do H2 para visualizar os dados do banco
spring.h2.console.path=/h2
spring.jpa.defer-datasource-initialization=true
```

### ℹ️ Explicação do `application.properties`
- 🔗 `spring.datasource.url`: Define a URL de conexão do banco H2 em memória.
- 🚗 `spring.datasource.driver-class-name`: Define o driver JDBC do H2.
- 🔐 `spring.datasource.username` e `spring.datasource.password`: Credenciais padrão do H2.
- 🔄 `spring.jpa.database-platform`: Define o dialeto do Hibernate para H2.
- 📌 `spring.jpa.hibernate.ddl-auto=update`: Permite que o Hibernate atualize automaticamente o esquema do banco de dados.
- 📊 `spring.jpa.show-sql=true`: Exibe no console as consultas SQL executadas.
- 🌐 `spring.h2.console.path=/h2`: Habilita o console web do H2, acessível em `http://localhost:8080/h2`.
- ⏳ `spring.jpa.defer-datasource-initialization=true`: Garante que as configurações do banco sejam carregadas corretamente.

## 🔐 Credenciais **Spring Security**
   - 👤 **Usuário**: `admin`
   - 🔑 **Senha**: `123`

## ▶️ Como Rodar o Projeto
1. 📥 Clone o repositório:
   ```sh
   git clone https://github.com/VavaHelper/Vava-API.git
   ```
2. 📂 Acesse o diretório do projeto:
   ```sh
   cd Vava-API
   ```
3. 🔀 Troque para a branch DSV:
   ```sh
   git checkout development
   ```
4. 🏗️ Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```
5. 🌍 Para acessar o console do H2, abra o navegador e digite:
   ```
   http://localhost:8080/h2
   ```
   Use as credenciais:
   - **JDBC URL**: `jdbc:h2:mem:apidsv`
   - **Usuário**: `sa`
   - **Senha**: `123`

## 📡 Endpoints Disponíveis
A API oferece os seguintes endpoints:

### 📝 Registro de Usuário
- **POST** `/auth/register`
- **Request Body:**
  ```json
  {
    "username": "exemplo",
    "email": "exemplo@email.com",
    "password": "senha123"
  }
  ```

### 🔑 Login de Usuário
- **POST** `/auth/login`
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

## 🔮 Futuras Implementações
- 🔄 Recuperação de senha
- 🏅 Perfis de usuários (Admin, Usuário Comum)
- 🔗 Integração com OAuth2
- 🎧 Atendimento/Suporte ao Usuário Cliente

## 📜 Licença
Este projeto está sob a licença MIT. Sinta-se à vontade para utilizá-lo e modificá-lo. ✨

