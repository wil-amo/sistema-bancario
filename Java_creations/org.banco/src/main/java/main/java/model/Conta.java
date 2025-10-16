package main.java.model;


public abstract class Conta implements Iconta {
    private static final int AGENCIA_PADRAO = 1272;
    private static int SEQUENCIAL = 1000;
    private final Cliente cliente;

    public Conta(main.java.model.Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = Math.random() * 10000;
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

//Ideal realizar a validação nos campos
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
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente para realizar o saque.");
        }
        this.saldo -= valor;
    }

    public void transferencia(double valor, Conta destino) {
        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino não pode ser nula.");
        }

        this.saque(valor);
        destino.deposito(valor);
    }

    protected void imprimirInfosComuns() {
        System.out.println("agencia : " + this.cliente.getNome());
        System.out.println("agencia : " + this.agencia);
        System.out.println("numero : " + this.numero);
        System.out.printf("saldo : %.2f%n", this.saldo); // mantém o formato para casas decimais
    }

}


