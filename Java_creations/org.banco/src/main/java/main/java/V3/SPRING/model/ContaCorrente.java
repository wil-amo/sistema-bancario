package main.java.V3.SPRING.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta {

    public ContaCorrente() {
        super();
    }

    public ContaCorrente(Cliente cliente, Integer agencia, Integer numero) {
        super(cliente, agencia, numero);
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "id=" + getId() +
                ", agencia=" + getAgencia() +
                ", numero=" + getNumero() +
                ", saldo=" + getSaldo() +
                ", ativo=" + isAtivo() +
                '}';
    }
}