package main.java.V2.JDBC.Model;

import main.java.V1.POO.Exception.InvalidContaException;
import main.java.V1.POO.Exception.MovimentacaoInvalidaException;
import main.java.V1.POO.Exception.TaxaExeception;
import main.java.V2.JDBC.Model.interfaces.Iconta;

import java.util.Objects;

public abstract class Conta implements Iconta, Comparable<Conta> {
    private static final int AGENCIA_PADRAO = 1272;
    private static final double TAXA_SAQUE = 0.05; //5%
    private static final double TAXA_TRANSFERENCIA = 0.01; //1%
    private boolean ativo;
    protected int agencia;
    protected int numero;
    protected double saldo;
    private int totalSaques;
    private final Cliente cliente;

    // gerado pelo banco
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    public Conta(Cliente cliente) {
        if (cliente == null) {
            throw new InvalidContaException("Cliente não pode ser nulo");
        }
        if (cliente.getCpf() == null || cliente.getLogin() == null) {
            throw new InvalidContaException("CPF ou Login são possuem dados " + cliente.getCpf() + " " + cliente.getLogin());
        }
        this.agencia = AGENCIA_PADRAO;
        this.cliente = cliente;
        this.saldo = 0.0;
        this.ativo = true;// conta sempre true na criação
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

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void deposito(double valor) {
        if (valor <= 0) {
            throw new MovimentacaoInvalidaException("O valor depósito DEVE ser maior que 0. Valor digitado foi de: ", valor);
        }
        this.saldo += valor;
    }

    public void saque(double valor) {
        aplicarTaxaSaque(valor);
        if (valor <= 0) {
            throw new MovimentacaoInvalidaException("O valor do saque deve ser positivo.", valor);
        }
        if (valor > saldo) {
            throw new MovimentacaoInvalidaException("Saldo insuficiente para realizar o saque. Valor atual em conta: ", getSaldo());
        }
        this.saldo -= valor;
        totalSaques++;
    }

    public void transferencia(double valor, Conta destino) {
        aplicarTaxaTransferencia(valor);
        if (destino == null) {
            throw new InvalidContaException("Conta está nula, operações não permitidas");
        }
        if (!destino.isAtivo()) {
            throw new InvalidContaException("Conta está INATIVA, operações não permitidas");
        }
        if (this.numero == destino.numero) {
            throw new InvalidContaException("Transação não realizada, saldo de saida é o mesmo de entrada");
        }
        this.saque(valor);
        destino.deposito(valor);
    }


    public void aplicarTaxaSaque(double valorSaque) {
        if (totalSaques > 0 && totalSaques % 3 == 0) {
            double taxa = valorSaque * TAXA_SAQUE;
            if (saldo <= 0) {
                throw new TaxaExeception("Saldo zerado", saldo);
            }
            this.saldo -= taxa;

        }
    }

    public void aplicarTaxaTransferencia(double valorTransferencia) {
        if (totalSaques > 0 && totalSaques % 6 == 0) {
            double taxa = valorTransferencia * TAXA_TRANSFERENCIA;
            if (saldo <= 0) {
                throw new TaxaExeception("Saldo zerado", saldo);
            }
            this.saldo -= taxa;
        }
    }

    //Resumo para saida de dados no output da UI(listarContasBtn)
    public String getResumo() {
        String sigla = (this instanceof ContaCorrente) ? "Conta-Corrente" : "Conta-Poupança";

        return String.format("%s | Ag: %d | Num: %d | Saldo: %.2f",
                sigla, this.agencia, this.numero, this.saldo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta conta)) return false;

        // Se Id, usa ID
        if (this.id > 0 && conta.id > 0) {
            return this.id == conta.id;
        }
        // Caso contrário usa o número
        return this.numero == conta.numero;
    }

    //> 0 usa Id ou numero
    @Override
    public int hashCode() {
        return Objects.hash(id > 0 ? id : numero);
    }

    @Override
    public int compareTo(Conta o) {
        return Integer.compare(this.numero, o.numero);
    }
}



