# Sistema Bancário – Java (POO → JDBC)

Projeto desenvolvido com foco em evolução arquitetural em Java, partindo de uma aplicação em memória (V1) até persistência real com JDBC e SQLite (V2).
O objetivo foi consolidar fundamentos essenciais de backend antes da utilização de frameworks.
---

## 📌 Versões

### 🔹 V1 – Aplicação em Memória (POO)

* Implementação orientada a objetos com Encapsulamento
* Herança e polimorfismo
* Separação em camadas (Model / Service)
* Exceções customizadas
* Collections
* Stream API para consultas


### Funcionalidades:

* Cadastro de clientes
* Cadastro de contas
* Depósito, saque e transferência
* Consultas utilizando Stream API
* Ordenação por saldo
* Filtros personalizados
* Top N contas


**Limitação: dados mantidos apenas em memória.**

---
### 🔹 V2 – Persistência com JDBC + MySQL
---
**Evolução do projeto para incluir persistência real de dados.**

* Conceitos aplicados:
* JDBC (DriverManager, Connection, PreparedStatement, ResultSet)
* MySQL como banco local
* Separação entre Repository e Service
* Uso de Optional
* try-with-resources
* Organização em camadas


### Funcionalidades:

* CRUD de Cliente
* CRUD de Conta
* Persistência em banco
* Transferência entre contas
* Validação de regras de negócio
---
## 🏗️ Arquitetura
---
### O projeto foi estruturado em:
- model → entidades do domínio
- repository → acesso a dados (SQL)
- service → regras de negócio
- config → configuração de conexão
- app → ponto de entrada

#### Essa divisão evita acoplamento entre regra de negócio e persistência.
---

## 🗄️ Banco de Dados
---
**MySQL**

### Tabelas principais:

* Cliente
* Conta

### Relacionamento:

* Conta vinculada a Cliente
#### Implementação de trigger para reforçar regras no nível do banco.

## 📚 Tecnologias

- Java 17+
- JDBC
- MySQL
- Git

## 🚀 Próximos Passos

* Implementação de transações explícitas
* Testes unitários
* API REST com Spring Boot
* JPA / Hibernate
 
Projeto com foco em aprendizado prático e consolidação de fundamentos de backend Java.
