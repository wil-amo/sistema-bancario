package main.java.V1.POO.Service;
import main.java.V1.POO.Model.*;
import java.util.*;


public class BancoService {

        private Banco banco;

        public BancoService(Banco banco) {
            this.banco = banco;
        }

    // Listagens
    public List<String> listarContasClientes() {
        return List.copyOf(banco.listarContasEClientes());
    }

    public List<Cliente> listarClientes() {
        return List.copyOf(banco.getClientes());
    }

    public List<Conta> listarContas() {
        return List.copyOf(banco.getContas());
    }

    public void adicionarCliente(Cliente cliente) {
        banco.addCliente(cliente);
    }

    // Adicionar conta vinculada a cliente
    public void adicionarConta(Cliente cliente, Conta conta) {
        banco.vinculaContaECliente(cliente, conta);
    }

    public void excluirConta(int numeroConta) {
        boolean removida = banco.removerConta(numeroConta);
        if (removida) {
            System.out.println("Conta " + numeroConta + " removida com sucesso.");
        } else {
            System.out.println("Conta " + numeroConta + " não encontrada.");
        }
    }
}
