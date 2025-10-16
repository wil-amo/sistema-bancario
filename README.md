# 💳 Sistema Bancário em Java

Uma aplicação simples com interface gráfica que simula operações bancárias reais: cadastro, login, depósito, saque e transferência.
Feita para praticar boas práticas, praticar o core da linguagem Java e aplicar conceitos como persistência em .txt, criptografia com SHA-256 e lógica sem frameworks.

## 🚀 Funcionalidades

- Cadastro de clientes com CPF, nome, login, senha e e-mail
- Login com verificação de senha e código de segurança
- Depósito, saque e transferência entre contas
- Interface gráfica com `JOptionPane`
- Registro de logins e operações em arquivos
- Geração de logs de sistema e erros
- Armazenamento de dados em arquivos `.txt`

<img width="481" height="225" alt="image" src="https://github.com/user-attachments/assets/5befbb5d-33ba-44a6-b995-10ab205ecae7" />
<img width="481" height="136" alt="image" src="https://github.com/user-attachments/assets/d6b9fbfc-d327-4d09-9bb9-6814da0372bd" />
<img width="481" height="34" alt="image" src="https://github.com/user-attachments/assets/ada7e924-bb24-472c-86a5-269bd7b4ae0b" />
<img width="481" height="135" alt="image" src="https://github.com/user-attachments/assets/a5ef4292-306c-49e7-a6ed-460958163a7e" />
<img width="481" height="144" alt="image" src="https://github.com/user-attachments/assets/85b83fb8-39f8-4349-b191-24461e9fc4e9" />



## 🛠️ Como executar

1. Clone o repositório:
   1.1 - git clone https://github.com/wil-amo/sistema-bancario.git
   1.2 Abra o projeto em uma IDE compatível com Maven (como IntelliJ IDEA ou Eclipse)
   1.3 Compile e execute a classe Main.java


2. Pré-requisitos:
- Java 8 ou superior instalado
- Maven instalado e configurado (mvn clean install)
- Conexão com a internet (para envio de e-mails)


  
3. Configuração de envio de e-mail:
- É necessário configurar um e-mail válido e uma senha de aplicativo (como no Gmail)
- Essas credenciais são usadas para enviar códigos de verificação por e-mail

  Exemplo de configuração no código na classe EmailUtil.java
- String emailRemetente = "seuemail@gmail.com";
- String senhaApp = "sua_senha_de_aplicativo";



4. Arquivos gerados automaticamente:
- Clientes-criados.txt: lista de clientes cadastrados
- Logins-registrados.txt: histórico de logins
- Registro-logins.txt: sessões iniciadas
- app-log.txt: log de erros e eventos



🤝 Contribuição

Contribuições são bem-vindas!
Sinta-se à vontade para abrir issues ou sugerir melhorias 

