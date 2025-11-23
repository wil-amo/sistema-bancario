package main.java.V1.POO.Model;

import main.java.V1.POO.Model.Cliente;
import main.java.V1.POO.Model.Conta;

public class ContaPoupanca extends Conta {


    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        cliente.setConta(this); //vincula essa conta ao cliente
    }

    @Override
    public void transferencia(double valor, main.java.V2.JDBC.Model.Conta destino) {
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("==== EXTRATO CONTA POUPANÇA ====");
        imprimirInfosComuns();
    }

    @Override
    public String toString() {
        return String.format("ContaPoupanca{cliente='%s', agencia=%d, numero=%d, saldo=%.2f}",
                super.getCliente().getNome(), super.getAgencia(), super.getNumero(), super.getSaldo());
    }

    @Override
    public int compareTo(Conta o) {
        return 0;
    }
}
