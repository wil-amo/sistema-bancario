package main.java.model.interfaces;

import main.java.model.Conta;

public interface Iconta {

    void  deposito(double valor);
    void saque(double valor);
    void transferencia(double valor, Conta destino);
    void imprimirExtrato();
    void aplicarTaxaSaque(double valor);
    void aplicarTaxaTransferencia(double valor);



}
