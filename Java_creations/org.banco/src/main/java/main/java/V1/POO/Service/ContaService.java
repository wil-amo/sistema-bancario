package main.java.V1.POO.Service;

import main.java.V1.POO.Model.Banco;
import main.java.V1.POO.Model.Conta;
import main.java.V1.POO.Model.interfaces.Iconta;

public class ContaService {
    private Banco banco;

    public ContaService(Banco banco) {
        this.banco=banco;
    }

    //IConta sendo utilizado para mais flexibilidade ex: criação de ContaDigital
    public void criaConta(String cpf , Iconta conta){
        banco.addConta(cpf,conta);
    }

    public void depositar(double valor, Iconta conta) {
        conta.deposito(valor);
        System.out.println("Depósito de " + valor + " realizado na conta " + ((Conta) conta).getNumero() +
                ". Saldo atual: " + ((Conta) conta).getSaldo());
    }

    public void sacar(double valor, Iconta conta) {
        conta.saque(valor);
        System.out.println("Saque de " + valor + " realizado na conta " + ((Conta) conta).getNumero() +
                ". Saldo atual: " + ((Conta) conta).getSaldo());
    }

    public void transferir(Iconta atual , double valor, Iconta destino) {
        System.out.println("Transferência de " + valor + " da conta " + ((Conta) atual).getNumero() +
                " para conta " + ((Conta) destino).getNumero());
                    atual.transferencia(valor,((Conta) destino));
                        System.out.println("Saldo origem: " + ((Conta) atual).getSaldo() + " | Saldo destino: " + ((Conta) destino).getSaldo());
    }
}