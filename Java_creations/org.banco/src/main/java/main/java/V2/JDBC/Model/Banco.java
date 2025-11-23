package main.java.V2.JDBC.Model;

import java.util.*;
import java.util.stream.Collectors;

public class Banco {
    private static final String NOME = "Banco do Povo";

    //evita lista null
    public Banco() {
        this.contas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Banco{" +
                "contas=" + contas +
                ", clientes=" + clientes +
                ", contasPorCliente=" + contasPorCliente +
                '}';

    }

    public List<Conta> getContas() {
        return contas;
    }

    private List<Conta> contas;

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

    //(conta e cliente - Final)
    private Map<Cliente, Conta> contasPorCliente = new HashMap<>();

    //vincula cliente e conta
    public void vincularConta(Cliente cliente, Conta conta) {
        contasPorCliente.put(cliente, conta);
    }

    public List<String> getContasEClientesVinculados() {
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

    public List<String> ordenaPorSaldo() {
        List<Conta> ordemSaldo = new ArrayList<>(contas);
        return ordemSaldo.stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
                .collect(Collectors.toList());
    }

    //Trabalha em conjunto com excluirConta
    // Exclui conta do map<Cliente,Conta> que estão vinculados
    public void excluirContaVinculada(int removerConta) {
        Iterator<Map.Entry<Cliente, Conta>> it = contasPorCliente.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Cliente, Conta> entry = it.next();
            if (entry.getValue().getNumero() == removerConta) {
                it.remove();
            }
        }
        System.out.println("Conta de número -> "+ removerConta+ "foi excluida com sucesso do cliente vinculado");
    }

    //Encontrei alguns padrões assim
//    boolean removido = contasPorCliente.entrySet().removeIf(
//            entry -> entry.getValue().getNumero() == removerConta
//    );
//
//if (removido) {
//        System.out.println("Conta " + removerConta + " excluída com sucesso.");
//    } else {
//        System.out.println("Conta " + removerConta + " não encontrada.");
//    }


    //Exclui contas apenas da lista de contas
    public void excluirConta(int removerConta) {
        if (contas == null) {
            System.out.println("Lista de contas não contem nada.");
            return;
        }
        try {
            contas.removeIf(conta -> conta != null && conta.numero == removerConta);
            System.out.println("Conta de número " + removerConta + " foi removida com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao remover contas: " + e.getMessage());
        }

    }

        public Conta buscaPorConta(int conta) {
        for (Conta c : contas) {
            if (c.getNumero() == conta) {
                return c;
            }
        }
        System.out.println("Conta não existe.");
        return null;
    }


    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        System.out.println("CPF não existe na base.");
        return null;
    }
}
