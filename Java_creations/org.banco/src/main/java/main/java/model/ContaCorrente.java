package main.java.model;

public class ContaCorrente extends main.java.model.Conta {


    public ContaCorrente(main.java.model.Cliente cliente) {
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
        return String.format("ContaCorrente{cliente='%s', agencia=%d, numero=%d, saldo=%.2f}",
                super.getCliente().getNome(), super.getAgencia(), super.getNumero(), super.getSaldo());
    }

}
