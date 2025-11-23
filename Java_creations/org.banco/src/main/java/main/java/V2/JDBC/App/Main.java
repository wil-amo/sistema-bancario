package main.java.V2.JDBC.App;
import main.java.V2.JDBC.Dao.ClienteDAO;
import main.java.V2.JDBC.DB.MySQLConnection;
import main.java.V2.JDBC.Infra.DbHealthCheck;
import main.java.V2.JDBC.Model.Cliente;

public class Main {
    public static void main(String[] args) {


        DbHealthCheck.healthCheckMySql();
        MySQLConnection connection = new MySQLConnection();

        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Cliente cliente1 = new Cliente("12345467891", "Cesar", "Cesar", "cesar@gmail.com");
        clienteDAO.insertCliente(cliente1);



        //Conta conta1 = new ContaCorrente(cliente1);
//        banco.adicionarCliente(cliente1);
//        banco.adicionarConta(conta1);
//        banco.vincularConta(cliente1,conta1);
//
//
//
//        Cliente cliente2 = new Cliente("0983456321", "Elton", "Elton", "1234","Elson@gmail.com");
//        Conta conta2 = new ContaCorrente(cliente2);
//        banco.adicionarCliente(cliente2);
//        banco.adicionarConta(conta2);
//        banco.vincularConta(cliente2,conta2);
//
//        Cliente cliente3 = new Cliente("09234454321", "Teo", "Teo", "1234","Teo@gmail.com");
//        Conta conta3 = new ContaCorrente(cliente3);
//        banco.adicionarCliente(cliente3);
//        banco.adicionarConta(conta3);
//        banco.vincularConta(cliente3,conta3);
//
//
//        Cliente cliente4 = new Cliente("098765431", "Jorge", "Jorge", "1234","Jorge@gmail.com");
//        Conta conta4= new ContaCorrente(cliente4);
//        banco.adicionarCliente(cliente4);
//        banco.adicionarConta(conta4);
//        banco.vincularConta(cliente4,conta4);
//
//        Cliente cliente5 = new Cliente("0987654391", "Ana", "Ana", "1234","Ana@gmail.com");
//        Conta conta5 = new ContaCorrente(cliente5);
//        banco.adicionarCliente(cliente5);
//        banco.adicionarConta(conta5);
//        banco.vincularConta(cliente5,conta5);
//
//        Cliente cliente6 = new Cliente("0987654391", "Pedro", "Pedro", "1234","Pedro@gmail.com");
//        Conta conta6 = new ContaPoupanca(cliente6);
//        banco.adicionarCliente(cliente6);
//        banco.adicionarConta(conta6);
//        banco.vincularConta(cliente6,conta6);






    }
    }


