# Sistema Bancário — Evolução Arquitetural em Java

```text
🟢 V1 → Java POO (Em memória)
          │
          ▼
🟡 V2 → JDBC + MySQL
          │
          ▼
🔵 V3 → Spring Boot + Spring Data JPA / Hibernate
```

> Projeto desenvolvido para simular a evolução de uma aplicação backend Java utilizada em ambientes corporativos, demonstrando a transição entre diferentes abordagens de persistência, arquitetura e organização de código.

O objetivo deste repositório é consolidar conhecimentos em desenvolvimento backend através da evolução progressiva da mesma aplicação, aproximando cada versão das tecnologias e padrões utilizados no mercado.

---

# 🎯 Objetivos

* Consolidar fundamentos de Java e Programação Orientada a Objetos
* Evoluir a persistência de dados utilizando JDBC e posteriormente Spring Data JPA
* Aplicar arquitetura em camadas
* Trabalhar com regras de negócio desacopladas
* Integrar Java com banco de dados relacional
* Demonstrar evolução arquitetural de forma incremental

---

# 🚀 Evolução do Projeto

## 🟢 V1 — Java Orientado a Objetos (Em Memória)

Primeira versão desenvolvida para consolidar os fundamentos da linguagem Java.

### Conceitos aplicados

* Encapsulamento
* Herança
* Polimorfismo
* Exceptions customizadas
* Collections
* Stream API
* Separação entre Model e Service

### Funcionalidades

* Cadastro de Clientes
* Cadastro de Contas
* Depósito
* Saque
* Transferência
* Consultas utilizando Stream API
* Ordenação por saldo
* Top N de contas

> Persistência realizada exclusivamente em memória.

---

## 🟡 V2 — JDBC + MySQL

Evolução da aplicação para persistência em banco de dados utilizando JDBC.

### Tecnologias

* Java
* JDBC
* MySQL
* Maven

### Conceitos aplicados

* DriverManager
* Connection
* PreparedStatement
* ResultSet
* Repository Pattern
* Service Layer
* Optional
* Try-With-Resources
* Controle Transacional (Commit / Rollback)

### Funcionalidades

* CRUD de Clientes
* CRUD de Contas
* Persistência em banco de dados
* Transferências entre contas
* Validação de regras de negócio
* Tratamento de exceções

### Regras implementadas no banco

#### Trigger — Inativação automática de contas

Quando um cliente é marcado como inativo, todas as contas vinculadas também são automaticamente inativadas.

**Objetivo**

* Garantir integridade dos dados
* Centralizar regras críticas no banco
* Evitar inconsistências entre Cliente e Conta

#### Trigger — Geração automática do número da conta

Responsável por gerar automaticamente o próximo número disponível para novas contas.

**Benefícios**

* Evita duplicidade
* Remove responsabilidade da aplicação
* Mantém consistência dos dados

---

## 🔵 V3 — Spring Boot + Spring Data JPA

Terceira evolução do projeto aproximando a aplicação de uma arquitetura utilizada em aplicações Java corporativas.

### Tecnologias

* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### Evoluções implementadas

* Mapeamento de entidades utilizando JPA
* Repository substituindo DAOs JDBC
* Persistência através do Hibernate
* Organização em camadas (Controller, Service, Repository e Model)
* Estrutura preparada para APIs REST
* Externalização das configurações da aplicação
* Collection do Postman para testes dos endpoints

---

# 📊 Comparativo das versões

| Característica | 🟢 V1           | 🟡 V2                | 🔵 V3                             |
| -------------- | --------------- | -------------------- | --------------------------------- |
| Persistência   | Memória         | JDBC                 | Spring Data JPA                   |
| Banco de Dados | —               | MySQL                | PostgreSQL                        |
| SQL            | Não             | Sim                  | JPA/Hibernate                     |
| Arquitetura    | Model / Service | Repository / Service | Controller / Service / Repository |
| API REST       | Não             | Não                  | Sim                               |
| ORM            | Não             | Não                  | Hibernate                         |

---

# 🏗️ Arquitetura

```text
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
JPA / Hibernate
      │
      ▼
PostgreSQL
```

O projeto segue uma arquitetura em camadas para promover baixo acoplamento, separação de responsabilidades e facilitar manutenção e evolução.

---

# 💾 Banco de Dados

**MySQL (V2)**

* Cliente
* Conta

**PostgreSQL (V3)**

* Persistência utilizando Spring Data JPA

As regras de negócio críticas foram distribuídas entre aplicação e banco de dados, utilizando triggers quando apropriado.

---

# 🛠️ Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* JDBC
* SQL
* MySQL
* PostgreSQL
* Maven
* Git
* Postman

---

# 📁 Recursos do Projeto

✔ Collection do Postman contendo os principais endpoints da API.

✔ Scripts SQL para criação do banco de dados.

✔ Triggers utilizadas na versão JDBC.

✔ Configuração da aplicação externalizada através do `application.properties`.

---

# 📚 Principais Aprendizados

Durante a evolução do projeto foram aplicados conceitos importantes utilizados no desenvolvimento backend Java:

* Programação Orientada a Objetos
* Arquitetura em Camadas
* Repository Pattern
* Persistência com JDBC
* Spring Data JPA
* Hibernate
* SQL
* Controle Transacional
* Triggers
* APIs REST
* Organização de projetos Maven

---

# 🔭 Próximas Evoluções

* ✅ Testes unitários com JUnit
* ✅ Documentação da API com Swagger / OpenAPI
* ✅ Docker para ambiente de desenvolvimento
* ✅ Spring Security com autenticação JWT
* ✅ Tratamento global de exceções (`@ControllerAdvice`)
* ✅ Validação de dados com Bean Validation
* ✅ Integração Contínua (GitHub Actions)
* ✅ Cobertura de testes com JaCoCo

---

> **Este projeto representa minha evolução em desenvolvimento backend Java, demonstrando a transição entre diferentes abordagens de persistência e arquitetura utilizadas no mercado.**
