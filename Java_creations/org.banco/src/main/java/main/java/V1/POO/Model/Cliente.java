package main.java.V1.POO.Model;

import main.java.V1.POO.Exception.DominioException;
import main.java.V1.POO.Model.interfaces.Iconta;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {
    private String  nome;
    private final String login;


    public String getCPF() {
        return CPF;
    }

    private final String CPF;
    private Conta conta;

    public  Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    private String email;

    public String getEmail() {
        return email;
    }


    public Cliente(String CPF, String nome, String login, String email) {
           // (exemplo: 11 dígitos)
           if (CPF == null || CPF.length() != 11) {
               throw new DominioException("CPF deve ter exatamente 11 dígitos.");
           }

           //(mínimo 3 caracteres)
           if (nome == null || nome.length() < 3) {
               throw new DominioException("Nome deve ter pelo menos 3 caracteres. O nome digitado foi: -> " + nome+ ".");
           }

           //(mínimo 5 caracteres e conter '.')
           if (login == null || login.length() <= 5 || !login.contains(".")) {
               throw new DominioException("Login deve ter pelo menos 5 caracteres e conter '.'");
           }

           //V2- Interessante verificar com alguma API ou serviço
           //Provedor min 2 digitos e existe @ no conteúdo
           if (email == null || !email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
               throw new DominioException("Email inválido.");
           }

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
        return String.format("Nome: %s | CPF: %s | Login: %s | Email: %s"+ "\n", nome, CPF, login, email);
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
        return this.nome.compareToIgnoreCase(o.nome);
    }


}



