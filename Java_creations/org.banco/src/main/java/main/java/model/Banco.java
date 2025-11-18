package main.java.model;

import java.util.*;
import java.util.stream.Collectors;

public class Banco {
    private static final String NOME = "Banco do Povo";

    //evita lista null
    public Banco() {
        this.contas = new ArrayList<>();
    }

    public List<Conta> getContas() {
        return contas;
    }

    private List<Conta> contas;
    //Conta conta;

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<ContaCorrente> getCorrentes() {
        List<ContaCorrente> correntes = new ArrayList<>();
        if (contas == null) {
            System.out.println("A lista de contas não foi inicializada.");
            return correntes; // Retorna vazia
        }
        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente) {//apenas CC
                correntes.add((ContaCorrente) conta);
            }
        }
        return correntes;
    }

    public List<ContaPoupanca> getPoupancas() {
        List<ContaPoupanca> poupancas = new ArrayList<>();
        if (contas == null) {
            System.out.println("A lista de contas poupanças não foi inicializada.");
            return poupancas; // Retorna vazia
        }
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {//apenas CC
                poupancas.add((ContaPoupanca) conta);//cast para evitar /castException
            }
        }
        return poupancas;
    }

    //CLIENTES
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    //encontra (conta e cliente)
    private Map<Cliente, Conta> contasPorCliente = new HashMap<>();

    //vincula cliente e conta
    public void vincularConta(Cliente cliente, Conta conta) {
        contasPorCliente.put(cliente, conta);
    }

    public List<String> contaEClienteVinculado() {
        List<String> vinculados = new ArrayList<>();
        for (Map.Entry<Cliente, Conta> entry : contasPorCliente.entrySet()) {
            Cliente cliente = entry.getKey();
            Conta conta = entry.getValue();
            String info = String.format("Cliente: %s | Conta: %d | Agência: %s | Saldo: %.2f",
                    cliente.getNome(),
                    conta.getNumero(),
                    conta.getAgencia(),
                    conta.getSaldo());
            vinculados.add(info);
        }
        return vinculados;
    }

    //ordemDecrescente
    public List<String> top3Saldos() {
        List<Conta> copiaContas = new ArrayList<>(contas);
        return copiaContas.stream()//olha todos itens da lista
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())//normal seria crescente
                .limit(3)
                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
                .collect(Collectors.toList());//organiza tudo em uma list
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}
