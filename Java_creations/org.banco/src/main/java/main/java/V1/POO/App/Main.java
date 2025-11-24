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

        Cliente cliente1 = new Cliente("12345678910", "Cesar", "Cesar.Rosa", "Cesar-Rosa@gmail.com");
        Cliente cliente2 = new Cliente("10987654321", "Maria", "Maria.Cruz", "Maria-Cruz@gmail.com");
        Cliente cliente3 = new Cliente("12345678123", "Lino", "Lino.Barros", "Lino-Barros@gmail.com");

        Conta conta1 = new ContaCorrente(cliente1);
        Conta conta2 = new ContaCorrente(cliente2);
        Conta conta3 = new ContaCorrente(cliente3);

        ContaService contaService1 = new ContaService(conta1);
        ContaService contaService2 = new ContaService(conta2);
        ContaService contaService3 = new ContaService(conta3);

        bancoService.adicionarCliente(cliente1);
        bancoService.adicionarCliente(cliente2);
        bancoService.adicionarCliente(cliente3);

        bancoService.adicionarConta(cliente1,conta1);
        bancoService.adicionarConta(cliente2,conta2);
        bancoService.adicionarConta(cliente3,conta3);

        System.out.println("Contas");
        bancoService.imprimirContas();

        System.out.println("\nClientes");
        bancoService.imprimirClientes();


//        bancoService.excluirConta(1000);


        System.out.println("Contas");
        bancoService.imprimirContas();

        System.out.println("\nClientes");
        bancoService.imprimirClientes();


        System.out.println("\n");
        contaService1.depositar(500);
        System.out.println("\n");
       contaService2.sacar(200);

        System.out.println("\n");
       contaService1.transferir(100, conta2);

        System.out.println("\n");

        System.out.println("Contas");
        bancoService.imprimirContas();

        System.out.println("\nClientes");
        bancoService.imprimirClientes();



    }
}











//        Cliente cliente1 = new Cliente("12345467891", "Cesar", "Cesar", "cesar@gmail.com");
//        Conta conta1 = new ContaCorrente(cliente1);
//        banco.adicionarCliente(cliente1);
//        banco.adicionarConta(conta1);
//        banco.vincularConta(cliente1, conta1);
//
//
//        Cliente cliente2 = new Cliente("0983456321", "Elton", "Elton",  "Elson@gmail.com");
//        Conta conta2 = new ContaCorrente(cliente2);
//        banco.adicionarCliente(cliente2);
//        banco.adicionarConta(conta2);
//        banco.vincularConta(cliente2, conta2);
//
//        Cliente cliente3 = new Cliente("09234454321", "Teo", "Teo", "Teo@gmail.com");
//        Conta conta3 = new ContaCorrente(cliente3);
//        banco.adicionarCliente(cliente3);
//        banco.adicionarConta(conta3);
//        banco.vincularConta(cliente3, conta3);
//
//
//        Cliente cliente4 = new Cliente("098765431", "Jorge", "Jorge",  "Jorge@gmail.com");
//        Conta conta4 = new ContaCorrente(cliente4);
//        banco.adicionarCliente(cliente4);
//        banco.adicionarConta(conta4);
//        banco.vincularConta(cliente4, conta4);
//
//        Cliente cliente5 = new Cliente("0987654391", "Ana", "Ana",  "Ana@gmail.com");
//        Conta conta5 = new ContaCorrente(cliente5);
//        banco.adicionarCliente(cliente5);
//        banco.adicionarConta(conta5);
//        banco.vincularConta(cliente5, conta5);
//
//        Cliente cliente6 = new Cliente("0987654391", "Pedro", "Pedro", "Pedro@gmail.com");
//        Conta conta6 = new ContaPoupanca(cliente6);
//        banco.adicionarCliente(cliente6);
//        banco.adicionarConta(conta6);
//        banco.vincularConta(cliente6, conta6);






