package main.java.model;

public class ContaPoupanca extends main.java.model.Conta {


    public ContaPoupanca(main.java.model.Cliente cliente) {
        super(cliente);
        cliente.setConta(this); //vincula essa conta ao cliente
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
