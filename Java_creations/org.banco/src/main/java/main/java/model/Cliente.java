package main.java.model;

import main.java.model.infra.HashUtil;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {
    private String  nome;
    private final String login;

//    public String getSenhaHash() {
//        return senhaHash;
//    }
//
//    private String senhaHash;

    public String getCPF() {
        return CPF;
    }

    private final String CPF;
    private main.java.model.Conta conta;

    public void setConta(main.java.model.Conta conta) {
        this.conta = conta;
    }



    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // precisa implementar
//    private String codigoVerificacao;
//    public String getCodigoVerificacao() {
//        return codigoVerificacao;
//    }
//
//    public void setCodigoVerificacao(String codigoVerificacao) {
//        this.codigoVerificacao = codigoVerificacao;
//    }
//    public void setSenhaHash(String senhaHash) {
//        this.senhaHash = senhaHash;
//    }

    public Cliente(String CPF, String nome, String login, String senha, String email) {
        this.CPF = CPF;
        this.nome = nome;
        this.login = login;
        //this.senhaHash = HashUtil.gerarHash(senha);
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
        return String.format("Cliente: %s | CPF: %s | Login: %s | Número Conta: %d", nome, CPF, login, conta.getNumero());
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
                //&& Objects.equals(senhaHash, cliente.senhaHash)
        //  && Objects.equals(codigoVerificacao, cliente.codigoVerificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, login, CPF, email);
//        return Objects.hash(nome, login, senhaHash, CPF, email, codigoVerificacao);

    }

    @Override
    public int compareTo(Cliente o) {
        return 0;
    }
}



