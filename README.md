# 🏦 Protótipo de Sistema Bancário – Java POO

Este repositório apresenta um **Protótipo de Sistema Bancário**, desenvolvido como exercício prático de **Programação Orientada a Objetos (POO)** e boas práticas de código.

O projeto demonstra conceitos de POO e se inspira em princípios de **SOLID** e boas práticas de **Clean Code**.



---

## 📌 Versões

### ✅ V1 – Implementação de POO
**Conceitos aplicados:** POO (encapsulamento, herança, polimorfismo, abstração), interfaces, exceções, collections e organização por pacotes.


### Operações
- Criar contas bancárias.
- Associar clientes às contas.
- Realizar depósitos e saques.
- Transferir valores entre contas.
- Exibir saldo e informações.
- Pesquisar dados de clientes e contas.

### Consultas com Stream API
Implementadas na classe `ConsultaService`:
- Busca de contas por critérios
- Ordenação de contas por saldo
- Listagem de contas filtradas
- Top N contas com maior saldo
- Uso de `Comparator`, `filter`, `sorted`, `limit`, `collect`

---


## 🧪 Exemplo de Uso

O fluxo principal é executado pela classe `Main`, que:
1. Cria o banco
2. Cria clientes
3. Cria e vincula contas
4. Executa operações bancárias
5. Realiza consultas usando Stream API
6. Exibe resultados no console

---

## 🚧 Limitações Atuais

- Persistência apenas em memória
- Não utiliza banco de dados
- Não possui testes automatizados
- Interface apenas via console

---

## 🔮 Próximos Passos (Planejados)

- V2: Persistência com JDBC
- V3: Migração para Spring Boot + JPA
- Testes unitários
- Camada de API REST

---

## 📚 Observações

Este projeto tem foco **educacional** e foi desenvolvido para consolidar fundamentos
sem uso de frameworks e persistência em banco de dados.
