package main.java.model;

import java.util.*;
import java.util.stream.Collectors;

public class Banco {
    private static final String NOME = "Banco do Povo";

    private List<Conta> contas;
    Conta conta;

    public Conta getConta() {
        return conta;
    }


    public Banco() {
        this.contas = new ArrayList<>();
    }


    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public String listarContas() {
        StringBuilder sb = new StringBuilder("=== LISTA DE CONTAS TOTAIS DO BANCO ===\n");
        for (Conta conta : contas) {
            sb.append(conta.toString()).append("\n");
        }
        return sb.toString();
    }

    public String listarClientes() {
        StringBuilder sb = new StringBuilder("=== LISTA DE CLIENTES ===\n");
        for (Cliente cliente : clientes) {
            sb.append(cliente.toString()).append("\n");
        }
        return sb.toString();
    }


    public void listarContasCorrente() {
        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente) {
                System.out.println(String.format("Agencia: %s", conta.getAgencia()));
                System.out.println(String.format("Cliente : %s", conta.getCliente().getNome()));
                System.out.println(String.format("Número: %d", conta.getNumero()));
                System.out.println(String.format("Saldo: %.2f", conta.getSaldo()));
            }
        }
    }

    public void listarContasPoupanca() {
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                System.out.println(String.format("Agencia: %s", conta.getAgencia()));
                System.out.println(String.format("Cliente : %s", conta.getCliente().getNome()));
                System.out.println(String.format("Número: %d", conta.getNumero()));
                System.out.println(String.format("Saldo: %.2f", conta.getSaldo()));
            }
        }
    }


    public String getNome() {
        return NOME;
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
        return copiaContas.stream()//olha tos itens da lista
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
