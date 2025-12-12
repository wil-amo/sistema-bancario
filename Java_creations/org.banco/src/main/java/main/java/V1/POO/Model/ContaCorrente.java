package main.java.V1.POO.Model;

public class ContaCorrente extends Conta {


    public ContaCorrente(Cliente cliente) {
        super(cliente);  // chama construtor da classe pai (Conta)
        cliente.setConta(this); // vincula cliente à conta corrente
    }

    @Override
    public String toString() {
        return String.format("ContaCorrente{Cliente: %s | Agencia: %d | Numero: %d | Saldo: %.2f \n",
                super.getCliente().getNome(), super.getAgencia(), super.getNumero(), super.getSaldo());
    }

    @Override
    public int compareTo(Conta o) {
        return Double.compare(this.getSaldo(), o.getSaldo());
    }
}
