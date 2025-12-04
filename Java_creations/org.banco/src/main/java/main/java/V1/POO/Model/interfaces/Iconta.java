package main.java.V1.POO.Model.interfaces;


import main.java.V1.POO.Model.Conta;

public interface Iconta {

    void deposito(double valor);

    void saque(double valor);

    void transferencia(double valor, Conta destino);

    void aplicarTaxaSaque(double valor);

    void aplicarTaxaTransferencia(double valor);
}
