package main.java.V1.POO.Service;
import main.java.V1.POO.Model.*;
import java.util.*;


public class BancoService {

        private Banco banco;

        public BancoService(Banco banco) {
            this.banco = banco;
        }

    // Listagens
    public Map<Cliente, List<Conta>> listarContasClientes() {
        return banco.getContasPorCliente(); //remove o lis da saida removi o copy para testar consultaPorcpf no clienteservice
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

    public void excluiConta(int numeroConta) {
        banco.excluiConta(numeroConta);
    }

    public List<Conta> buscarContasPorCpf(String cpf) {
        for (Map.Entry<Cliente, List<Conta>> entry : banco.getContasPorCliente().entrySet()) {
            Cliente cliente = entry.getKey();
            if (cliente.getCPF().equals(cpf)) {
                return entry.getValue();
            }
        }
        return Collections.emptyList();
    }
}
