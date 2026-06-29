package main.java.V2.JDBC.Model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        cliente.setConta(this); // vincula cliente à conta corrente
    }

    @Override
    public String toString() {
        return String.format("ContaPoupanca{Cliente: %s | Agencia: %d | Numero: %d | Saldo: %.2f \n",
                super.getCliente().getNome(), super.getAgencia(), super.getNumero(), super.getSaldo());
    }

}
