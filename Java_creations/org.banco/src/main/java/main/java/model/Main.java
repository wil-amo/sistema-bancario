package main.java.model;

import main.java.model.service.Sessao;



import static main.java.model.infra.Exception.esperar;
import static main.java.model.infra.Exception.i;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Banco banco = new Banco();
        Sessao sessao = new Sessao();

        Cliente cliente1 = new Cliente("123454678910", "Cesar", "Cesar", "1234","cesar@gmail.com");
        Conta conta1 = new ContaCorrente(cliente1);

        banco.adicionarCliente(cliente1);
        banco.adicionarConta(conta1);
        banco.vincularConta(cliente1,conta1);
        sessao.registrarNovoCliente(cliente1);



        Cliente cliente2 = new Cliente("0987654321", "Elton", "Elton", "1234","Elson@gmail.com");
        Conta conta2 = new ContaCorrente(cliente2);
        banco.adicionarCliente(cliente2);
        banco.adicionarConta(conta2);
        banco.vincularConta(cliente2,conta2);
        sessao.registrarNovoCliente(cliente2);

        Cliente cliente3 = new Cliente("0987654321", "Teo", "Teo", "1234","Teo@gmail.com");
        Conta conta3 = new ContaCorrente(cliente3);
        banco.adicionarCliente(cliente3);
        banco.adicionarConta(conta3);
        banco.vincularConta(cliente3,conta3);
        sessao.registrarNovoCliente(cliente3);


        Cliente cliente4 = new Cliente("0987654321", "Jorge", "Jorge", "1234","Jorge@gmail.com");
        Conta conta4= new ContaCorrente(cliente4);
        banco.adicionarCliente(cliente4);
        banco.adicionarConta(conta4);
        banco.vincularConta(cliente4,conta4);
        sessao.registrarNovoCliente(cliente4);

        Cliente cliente5 = new Cliente("0987654321", "Ana", "Ana", "1234","Ana@gmail.com");
        Conta conta5 = new ContaCorrente(cliente5);
        banco.adicionarCliente(cliente5);
        banco.adicionarConta(conta5);
        banco.vincularConta(cliente5,conta5);
        sessao.registrarNovoCliente(cliente5);













    }
    }


