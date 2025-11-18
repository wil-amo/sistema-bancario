package main.java.model;


import java.util.Objects;

public abstract class Conta implements Iconta,Comparable<Conta> {
    private static final int AGENCIA_PADRAO = 1272;
    private static int SEQUENCIAL = 1000;
    private static final double TAXA_SAQUE = 0.05; //5%
    private static final double TAXA_TRANSFERENCIA = 0.01; //1%

    private final Cliente cliente;
    private int totalSaques;

    public Conta(main.java.model.Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = Math.round(Math.random() * 10000 * 100) / 100.0; //para 2 casas decimais saldo / 100
    }


    public Cliente getCliente() {
        return cliente;
    }


    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected int agencia;
    protected int numero;
    protected double saldo;

    public void deposito(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }

        this.saldo += valor;
    }

    public void saque(double valor) {
        aplicarTaxaSaque(valor);
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente para realizar o saque.");
        }
        this.saldo -= valor;
        totalSaques++;
    }

    public void transferencia(double valor, Conta destino) {
        aplicarTaxaTransferencia(valor);
        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino não pode ser nula.");
        }
        this.saque(valor);
        destino.deposito(valor);
    }

    protected void imprimirInfosComuns() {
        System.out.println("Nome : " + this.cliente.getNome());
        System.out.println("Agencia : " + this.agencia);
        System.out.println("Numero : " + this.numero);
        System.out.printf("Saldo : %.2f%n", this.saldo);
    }


    public void aplicarTaxaSaque(double valorSaque){
        if(totalSaques > 0 && totalSaques % 3 == 0){
            double taxa = valorSaque * TAXA_SAQUE;
            this.saldo -= taxa;
            this.totalSaques=-4;

        }
    }

    public void aplicarTaxaTransferencia(double valorSaque){
        if(totalSaques > 0 && totalSaques % 6 == 0){
            double taxa = valorSaque * TAXA_TRANSFERENCIA;
            this.saldo -= taxa;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return totalSaques == conta.totalSaques
                && agencia == conta.agencia
                && numero == conta.numero
                && Double.compare(saldo, conta.saldo) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalSaques, agencia, numero, saldo);
    }
}


