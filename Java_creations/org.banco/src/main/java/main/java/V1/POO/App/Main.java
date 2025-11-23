import main.java.V1.POO.Model.Cliente;
import main.java.V1.POO.Model.Conta;
import main.java.V1.POO.Model.ContaCorrente;
import main.java.V1.POO.Model.ContaPoupanca;
import main.java.V1.POO.Service.ContaService;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("12345678910", "Cesar", "cesar", "cesar@gmail.com");
        Cliente cliente2 = new Cliente("10987654321", "Maria", "maria", "maria@gmail.com");

        Conta conta1 = new ContaCorrente(cliente1);
        Conta conta2 = new ContaCorrente(cliente2);

        ContaService contaService1 = new ContaService(conta1);
        ContaService contaService2 = new ContaService(conta2);

        // Conferir Operações e implementar o restante para migração completa
        contaService1.depositar(500);
        contaService2.sacar(200);
        contaService1.transferir(100, conta2);
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






