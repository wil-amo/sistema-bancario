package main.java.V2.JDBC.Model.interfaces;

import main.java.V2.JDBC.Model.Conta;

public interface Iconta {

    void  deposito(double valor);
    void saque(double valor);
    void transferencia(double valor, Conta destino);
    void imprimirExtrato();
    void aplicarTaxaSaque(double valor);
    void aplicarTaxaTransferencia(double valor);



}
