# 🏦 Protótipo de Sistema Bancário – Java POO

Este repositório apresenta um **Protótipo de Sistema Bancário**, desenvolvido como exercício prático de **Programação Orientada a Objetos (POO)** e boas práticas de código.

O projeto demonstra domínio dos principais conceitos de POO e se inspira em princípios de **SOLID** e boas práticas de **Clean Code**.



---

## 📌 Versões

### ✅ V1 – Implementação de POO
Nesta versão, o sistema cobre os pilares da POO e aplica recursos importantes da linguagem Java:

- **Encapsulamento** → Atributos privados e acesso controlado via getters/setters.
- **Herança** → Para reutilização de código.
- **Polimorfismo** → Métodos sobrescritos e comportamento dinâmico.
- **Abstração** → Uso de **classe abstrata** para definir contrato comun.
- **Interfaces** → Definição de regras implementadas por diferentes classes.
- **Tratamento de exceções** → Uso de `try/catch` e pacote de Exception.
- **Coleções Java** → Gerenciamento de contas e clientes com listas.
- **Organização em pacotes** → Modularidade e separação de responsabilidades.

#### 🔑 Princípios SOLID em meu projeto
- **SRP (Single Responsibility Principle)**: a classe Conta só gerencia operações da conta, sem misturar lógica de cliente.
- **OCP (Open/Closed Principle)**: você pode criar novas contas (ContaCorrente, ContaPoupanca) herdando de Conta sem alterar código existente.
- **LSP (Liskov Substitution Principle)**: qualquer classe filha de Conta pode substituir a classe base sem quebrar o sistema.
- **ISP (Interface Segregation Principle)**: (ex: Iconta), elas mantêm contratos enxutos.
- **DIP (Dependency Inversion Principle)**: quando serviços dependem de abstrações (interfaces) em vez de classes concretas.


#### ✨ Clean Code
- Nomes claros e descritivos.
- Código modular e reutilizável.
- Estrutura organizada em pacotes.
- Comentários apenas quando necessário (autoexplicativo).

Funcionalidades:
- Criar contas bancárias.
- Associar clientes às contas.
- Realizar depósitos e saques.
- Transferir valores entre contas.
- Exibir saldo e informações.
- Pesquisar dados de clientes e contas.

---

### 🚧 V2 – Em Construção
A nova versão está em desenvolvimento e terá:
- Persistência em banco de dados.
- Autenticação de usuários.
- Expansão das funcionalidades bancárias.

---

## 🛠️ Tecnologias
- **Java** (versão 8 ou superior)
- Paradigma de **Programação Orientada a Objetos**
- Inspiração em princípios de **SOLID** e **Clean Code**
---

## ▶️ Como Executar
1. Clone este repositório:
   ```bash
   git clone https://github.com/wil-amo/sistema-bancario.git