package main.java.V3.SPRING.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(Cliente cliente, Integer agencia, Integer numero) {
        super(cliente, agencia, numero);
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "id=" + getId() +
                ", agencia=" + getAgencia() +
                ", numero=" + getNumero() +
                ", saldo=" + getSaldo() +
                ", ativo=" + isAtivo() +
                '}';
    }
}