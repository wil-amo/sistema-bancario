package main.java.V1.POO.Service;

import main.java.V1.POO.Model.Conta;

public class ContaService {
    private Conta conta;

    public ContaService(Conta conta) {
        this.conta = conta;
    }

    public void depositar(double valor) {
        conta.deposito(valor);
        System.out.println("Depósito de " + valor + " realizado na conta " + conta.getNumero() +
                ". Saldo atual: " + conta.getSaldo());
    }

    public void sacar(double valor) {
        conta.saque(valor);
        System.out.println("Saque de " + valor + " realizado na conta " + conta.getNumero() +
                ". Saldo atual: " + conta.getSaldo());
    }

    public void transferir(double valor, Conta destino) {
        conta.transferencia(valor, destino);
        System.out.println("Transferência de " + valor + " da conta " + conta.getNumero() +
                " para conta " + destino.getNumero());
        System.out.println("Saldo origem: " + conta.getSaldo() + " | Saldo destino: " + destino.getSaldo());
    }


}