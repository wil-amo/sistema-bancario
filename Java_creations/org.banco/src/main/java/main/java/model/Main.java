package main.java.model;

import main.java.model.service.Sessao;

import javax.swing.*;

import static main.java.model.infra.Exception.esperar;

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

        System.out.println(banco.contaEClienteVinculado());

        System.out.println(conta1.getSaldo());
        conta1.saque(1000);
        conta1.saque(1000);
        conta1.saque(1000);
        esperar(1000);

        System.out.println(conta1.getSaldo());
        esperar(1000);

        conta1.saque(1000);
        esperar(1000);

        System.out.println(conta1.getSaldo());






    }
    }


