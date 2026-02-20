package main.java.V2.JDBC.Model;


import main.java.V1.POO.Exception.DominioException;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {
    private String nome;
    private String login;
    private boolean ativo;
    public Cliente() {}


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLogin(String login){
        this.login = login;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String cpf;
    private Conta conta;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    private String email;

    public String getEmail() {
        return email;
    }


    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente(String cpf, String nome, String login, String email, boolean ativo) {
        // (exemplo: 11 dígitos)
        if (cpf == null || cpf.length() != 11) {
                        throw new DominioException("CPF deve ter exatamente 11 dígitos.");
        }

        //(mínimo 3 caracteres)
        if (nome == null || nome.length() < 3) {
            throw new DominioException("Nome deve ter pelo menos 3 caracteres. O nome digitado foi: -> " + nome + ".");
        }

        //(mínimo 5 caracteres e conter '.')
        if (login == null || login.length() <= 5 || !login.contains(".")) {
            throw new DominioException("Login deve ter pelo menos 5 caracteres e conter '.'");
        }

        //V2- Interessante verificar com alguma API ou serviço
        //Provedor min 2 digits e existe @ no conteúdo
        if (email == null || !email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new DominioException("Email inválido.");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.ativo=ativo;
    }
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }


    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | CPF: %s | Login: %s | Email: %s | Ativo: %s%n",
                id, nome, cpf, login, email, ativo ? "true" : "false"
        );
    }


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cliente cliente)) return false;
    return Objects.equals(cpf, cliente.cpf);
}

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public int compareTo(Cliente o) {
        int i = this.nome.compareToIgnoreCase(o.nome);
        if (i == 0) {
            return this.cpf.compareTo(o.cpf);
        }// 0 iguais
        return i;
    }
}


