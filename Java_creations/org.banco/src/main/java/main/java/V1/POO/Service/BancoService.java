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
        return banco.listarContasEClientes();
    }

    public List<Cliente> listarClientes() {
        return banco.getClientes();
    }

    public List<Conta> listarContas() {
        return banco.getContas();
    }

    // Interface interna para validação
    public interface Validavel {
        void validar();
    }
    public void validador(Validavel v) {
        if (v == null) {
            System.out.println("Objeto é nulo");
        } else {
            v.validar();
        }
    }

    // Valida existência de conta no banco
    public void validarContaNoBanco(Conta conta) {
        if (conta == null) {
            System.out.println("Conta é nula");
        } else if (!banco.getContas().contains(conta)) {
            System.out.println("Conta não está registrada no banco");
        } else {
            System.out.println("Conta registrada no banco");
        }
    }

    // Adicionar cliente
    public void adicionarCliente(Cliente cliente) {
        validador(cliente);
        banco.addCliente(cliente);
    }

    // Adicionar conta vinculada a cliente
    public void adicionarConta(Cliente cliente, Conta conta) {
        validador(cliente);
        validador(conta);
        banco.vinculaContaECliente(cliente, conta);
    }

    // Vincular conta e cliente
    public void vinculaContaECliente(Cliente cliente, Conta conta) {
        banco.vinculaContaECliente(cliente, conta);
    }

    // Excluir conta por número
    public void excluirConta(int numeroConta) {
        boolean removida = banco.removerConta(numeroConta);
        if (removida) {
            System.out.println("Conta " + numeroConta + " removida com sucesso.");
        } else {
            System.out.println("Conta " + numeroConta + " não encontrada.");
        }
    }
}
