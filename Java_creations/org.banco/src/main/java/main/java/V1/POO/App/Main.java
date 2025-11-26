package main.java.V1.POO.App;

import main.java.V1.POO.Model.Banco;
import main.java.V1.POO.Model.Cliente;
import main.java.V1.POO.Model.Conta;
import main.java.V1.POO.Model.ContaCorrente;
import main.java.V1.POO.Service.BancoService;
import main.java.V1.POO.Service.ContaService;


public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("BANCO XP");
        BancoService bancoService = new BancoService(banco);
        // ConsultaService consultaService = new ConsultaService(banco);

        Cliente cliente1 = new Cliente("12345678901", "Cesar", "Cesar.Rosa", "Cesar-Rosa@gmail.com");
        Cliente cliente2 = new Cliente("10987654321", "Maria", "Maria.Cruz", "Maria-Cruz@mail.com");
        // Cliente cliente3 = new Cliente("12345678901", "Cesar", "Cesar.Rosa", "Cesar-Rosa@gmail.com");

        Conta conta1 = new ContaCorrente(cliente1);
        Conta conta2 = new ContaCorrente(cliente2);
//     Conta conta3 = new ContaCorrente(cliente3);

        ContaService contaService1 = new ContaService(conta1);
        //ContaService contaService2 = new ContaService(conta2);
//        ContaService contaService3 = new ContaService(conta3);

        bancoService.adicionarCliente(cliente1);


//        bancoService.adicionarCliente(cliente2);
//        bancoService.adicionarCliente(cliente3);

        bancoService.adicionarConta(cliente1, conta1);
        bancoService.adicionarConta(cliente2, conta2);

        System.out.println(bancoService.listarContasClientes());
        System.out.println("\n");
        contaService1.depositar(1);
        contaService1.transferir(2000, conta2);
        System.out.println("\n");
        System.out.println(bancoService.listarContasClientes());


    }
}






