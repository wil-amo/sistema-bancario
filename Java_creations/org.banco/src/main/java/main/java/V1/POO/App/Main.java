package main.java.V1.POO.App;

import main.java.V1.POO.Model.*;
import main.java.V1.POO.Service.BancoService;
import main.java.V1.POO.Service.ClienteService;
import main.java.V1.POO.Service.ContaService;
import main.java.V1.POO.Model.Conta;


public class Main {
    public static void main(String[] args) {
        //Banco
        Banco banco = new Banco("BANCO XP");
        BancoService bancoService = new BancoService(banco);

        //Clientes
        ClienteService clienteService = new ClienteService(banco);
        clienteService.criaCliente(new Cliente("12345678901", "Cesar", "Cesar.Rosa", "Cesar-Rosa@gmail.com"));
        clienteService.criaCliente(new Cliente("09876543210", "Paulo", "Paulo.Santos", "Paulo-Santos@gmail.com"));
        clienteService.criaCliente(new Cliente("00000000000", "Ale", "Ale.Jordan", "Ale-Jordan@gmail.com"));
        clienteService.criaCliente(new Cliente("00000000000", "Ale", "Ale.Jordan", "Ale-Jordan@gmail.com"));

        Cliente cliente1 = banco.getClientesPorCpf().get("12345678901");
        Cliente cliente2 = banco.getClientesPorCpf().get("09876543210");
        Cliente cliente3 = banco.getClientesPorCpf().get("00000000000");

        //Contas
        Conta conta1 = new ContaCorrente(cliente1);
        Conta conta2 = new ContaPoupanca(cliente2);
        Conta conta3 = new ContaPoupanca(cliente3);
        Conta conta4 = new ContaPoupanca(cliente3);

        //ContaService
        ContaService contaService1 = new ContaService(banco);
        contaService1.criaConta(conta1.getCliente().getCPF(), conta1);
        contaService1.criaConta(conta2.getCliente().getCPF(), conta2);
        contaService1.criaConta(conta3.getCliente().getCPF(), conta3);
        contaService1.criaConta(conta3.getCliente().getCPF(), conta4);




        //Operações
        contaService1.depositar(500, conta1);
        contaService1.sacar(1000, conta1);
        contaService1.depositar(100, conta2);
        contaService1.transferir(conta1, 200, conta2);

        //Atualiza cliente
        if (clienteService.atualizaCliente("00000000000",
                new Cliente("00000000000", "Ramon", "Ramon.login", "Ramon@gmail.com"))){
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }

        //Bancoservice
        for (String linha : bancoService.mostrarContasDoCliente("00000000000")) {
            System.out.println(linha);
        }
        bancoService.excluirConta(conta3.getNumero());

        bancoService.mostrarContasEClientes();


        //Consultas
//                System.out.println("Contas do CPF 12345678901:");
//                System.out.println(bancoService.buscarContasPorCpf("12345678901"));


    }
}