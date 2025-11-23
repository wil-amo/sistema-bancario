package main.java.V1.POO.Model;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {
    private String  nome;
    private final String login;


    public String getCPF() {
        return CPF;
    }

    private final String CPF;
    private Conta conta;

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Cliente(String CPF, String nome, String login, String email) {
        this.CPF = CPF;
        this.nome = nome;
        this.login = login;
        this.email = email;
    }
    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }


    @Override
    public String toString() {
        return String.format("Cliente: %s | CPF: %s | Login: %s | Número Conta: %d ", nome, CPF, login, conta.getNumero());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome)
                && Objects.equals(login, cliente.login)
                && Objects.equals(CPF, cliente.CPF)
                && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, login, CPF, email);
    }

    @Override
    public int compareTo(Cliente o) {
        return 0;
    }


}



