package main.java.V1.POO.App;

import main.java.V1.POO.Model.*;
import main.java.V1.POO.Service.BancoService;
import main.java.V1.POO.Service.ContaService;
import main.java.V1.POO.Model.Conta;


public class Main {
    public static void main(String[] args) {
                //Banco
                Banco banco = new Banco("BANCO XP");
                BancoService bancoService = new BancoService(banco);

                //Clientes
                Cliente cliente1 = new Cliente("12345678901", "Cesar", "Cesar.Rosa", "Cesar-Rosa@gmail.com");
                Cliente cliente2 = new Cliente("10987654321", "Maria", "Maria.Cruz", "Maria-Cruz@mail.com");
                Cliente cliente3 = new Cliente("12345678910", "Jose", "Jose.Rosa", "Jose-Rosa@gmail.com");

                //Contas
                Conta conta1 = new ContaCorrente(cliente1);
                Conta conta2 = new ContaPoupanca(cliente1);
                Conta conta3 = new ContaCorrente(cliente3);
                Conta conta4 = new ContaPoupanca(cliente3);

                //ContaService
                ContaService contaService1 = new ContaService(conta1);
                ContaService contaService2 = new ContaService(conta2);
                ContaService contaService3 = new ContaService(conta3);
                ContaService contaService4 = new ContaService(conta4);

                //Registro de Clientes no Banco ===
                bancoService.adicionarCliente(cliente1);
                bancoService.adicionarCliente(cliente2);
                bancoService.adicionarCliente(cliente3);

                //Registro de Contas no Banco ===
                bancoService.adicionarConta(cliente1, conta1);
                bancoService.adicionarConta(cliente1, conta2);
                bancoService.adicionarConta(cliente3, conta3);
                bancoService.adicionarConta(cliente3, conta4);

                //Consultas
                System.out.println("Contas do CPF 12345678901:");
                System.out.println(bancoService.buscarContasPorCpf("12345678901"));

                System.out.println("\nLista de Clientes:");
                System.out.println(bancoService.listarClientes());

                System.out.println("\nLista de Contas:");
                System.out.println(bancoService.listarContas());

                System.out.println("\nMapa Cliente -> Contas:");
                System.out.println(bancoService.listarContasClientes());

                //Operações
                contaService1.transferir(500, conta2);
                contaService1.sacar(250);
                contaService2.depositar(1000);
                contaService3.transferir(300, conta4);
            }
        }












