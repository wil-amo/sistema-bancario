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
        System.out.printf("Depósito de: %.2f realizado na conta: %d e Saldo atual é : %.2f\n", valor,  ((Conta) conta).getNumero(), ((Conta) conta).getSaldo());


    }

    public void sacar(double valor, Iconta conta) {
        conta.saque(valor);
        System.out.printf("Saque de: %.2f realizado na conta: %d e Saldo atual é : %.2f\n", valor,  ((Conta) conta).getNumero(), ((Conta) conta).getSaldo());

    }

    public void transferir(Iconta atual , double valor, Iconta destino) {
        System.out.printf("Transferência de: %.2f da conta: %d  para a conta: %d", valor,  ((Conta) atual).getNumero(), ((Conta) destino).getNumero());
        atual.transferencia(valor,((Conta) destino));
                        System.out.printf(" -> Saldo origem: %.2f | Saldo destino: %.2f" , ((Conta) atual).getSaldo(), ((Conta) destino).getSaldo());
    }
}