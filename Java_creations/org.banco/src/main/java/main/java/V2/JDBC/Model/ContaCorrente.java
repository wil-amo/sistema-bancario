package main.java.V2.JDBC.Model;

public class ContaCorrente extends Conta {


    public ContaCorrente(Cliente cliente) {
        super(cliente);
        cliente.setConta(this); // vincula conta ao cliente
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("==== EXTRATO CONTA CORRENTE ====");
        imprimirInfosComuns();
    }

    @Override
    public String toString() {
        return String.format("ContaCorrente{cliente='%s', agencia=%d, numero=%d, saldo=%.2f}\n",
                super.getCliente().getNome(), super.getAgencia(), super.getNumero(), super.getSaldo());
    }

    @Override
    public int compareTo(Conta o) {
        return 0;
    }
}
