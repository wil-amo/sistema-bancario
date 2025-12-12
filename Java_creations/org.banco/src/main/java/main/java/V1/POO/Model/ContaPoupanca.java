package main.java.V1.POO.Model;

import main.java.V1.POO.Model.Cliente;
import main.java.V1.POO.Model.Conta;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        cliente.setConta(this);
        super.saldo = 0;
    }

    @Override
    public String toString() {
        return String.format("ContaPoupanca{Cliente: %s | Agencia: %d | Numero: %d | Saldo: %.2f \n",
                super.getCliente().getNome(), super.getAgencia(), super.getNumero(), super.getSaldo());
    }

    @Override
    public int compareTo(Conta o) {
        return Double.compare(this.getSaldo(), o.getSaldo());
    }
}
