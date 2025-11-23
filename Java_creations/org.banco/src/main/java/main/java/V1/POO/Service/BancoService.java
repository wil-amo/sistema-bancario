package main.java.V1.POO.Service;
import main.java.V1.POO.Model.*;
import java.util.*;


public class BancoService {

        private Banco banco;
        private Map<Cliente, List<Conta>> contasPorCliente = new HashMap<>();

        public BancoService(Banco banco) {
            this.banco = banco;
        }

        public void adicionarCliente(Cliente cliente) {
            banco.getClientes().add(cliente);
            contasPorCliente.putIfAbsent(cliente, new ArrayList<>());
        }

        public void adicionarConta(Cliente cliente, Conta conta) {
            banco.getContas().add(conta);
            contasPorCliente.computeIfAbsent(cliente, k -> new ArrayList<>()).add(conta);
        }

        public void excluirConta(int numeroConta) {
            banco.getContas().removeIf(c -> c.getNumero() == numeroConta);
            contasPorCliente.values().forEach(lista -> lista.removeIf(c -> c.getNumero() == numeroConta));
        }

        public Map<Cliente, List<Conta>> getContasPorCliente() {
            return contasPorCliente;
        }
    }
//    private static final String NOME = "Banco";
//
//    //evita lista null
//    public BancoService() {
//        this.contas = new ArrayList<>();
//    }
//
//    @Override
//    public String toString() {
//        return "Banco{" +
//                "contas=" + contas +
//                ", clientes=" + clientes +
//                ", contasPorCliente=" + contasPorCliente +
//                '}';
//
//    }
//
//    public List<Conta> getContas() {
//        return contas;
//    }
//
//    private List<Conta> contas;
//
//    public void adicionarConta(Conta conta) {
//        if(conta.getCliente() != null) {
//            contas.add(conta);
//        }else {
//                System.out.println("Cliente não existe (Null)");
//            }
//
//        }
//
//    public List<ContaCorrente> getCorrentes() {
//        List<ContaCorrente> correntes = new ArrayList<>();
//        if (contas == null) {
//            System.out.println("A lista de contas não foi inicializada.");
//            return correntes; // Retorna vazia
//        }
//        for (Conta conta : contas) {
//            if (conta instanceof ContaCorrente) {//apenas CC
//                correntes.add((ContaCorrente) conta);
//            }
//        }
//        return correntes;
//    }
//
//    public List<ContaPoupanca> getPoupancas() {
//        List<ContaPoupanca> poupancas = new ArrayList<>();
//        if (contas == null) {
//            System.out.println("A lista de contas poupanças não foi inicializada.");
//            return poupancas; // Retorna vazia
//        }
//        for (Conta conta : contas) {
//            if (conta instanceof ContaPoupanca) {//apenas CC
//                poupancas.add((ContaPoupanca) conta);//cast para evitar /castException
//            }
//        }
//        return poupancas;
//    }
//
//    //CLIENTES
//    private List<Cliente> clientes = new ArrayList<>();
//
//    public void adicionarCliente(Cliente cliente) {
//        clientes.add(cliente);
//    }
//
//    public List<Cliente> getClientes() {
//        return clientes;
//    }
//
//    //(conta e cliente - Final)
//    private Map<Cliente, Conta> contasPorCliente = new HashMap<>();
//
//    //vincula cliente e conta
//    public void vincularConta(Cliente cliente, Conta conta) {
//        contasPorCliente.put(cliente, conta);
//    }
//
//    public List<String> getContasEClientesVinculados() {
//        List<String> vinculados = new ArrayList<>();
//        for (Map.Entry<Cliente, Conta> entry : contasPorCliente.entrySet()) {
//            Cliente cliente = entry.getKey();
//            Conta conta = entry.getValue();
//            String info = String.format("Cliente: %s | Conta: %d | Agência: %s | Saldo: %.2f",
//                    cliente.getNome(),
//                    conta.getNumero(),
//                    conta.getAgencia(),
//                    conta.getSaldo());
//            vinculados.add(info);
//        }
//        return vinculados;
//    }
//
//
//    //ordemDecrescente
//    public List<String> top3Saldos() {
//        List<Conta> copiaContas = new ArrayList<>(contas);
//        return copiaContas.stream()//olha todos itens da lista
//                .sorted(Comparator.comparing(Conta::getSaldo).reversed())//normal seria crescente
//                .limit(3)
//                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
//                .collect(Collectors.toList());//organiza tudo em uma list
//    }
//
//    public List<String> ordenaPorSaldo() {
//        List<Conta> ordemSaldo = new ArrayList<>(contas);
//        return ordemSaldo.stream()
//                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
//                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
//                .collect(Collectors.toList());
//    }
//
//    //Trabalha em conjunto com excluirConta
//    // Exclui conta do map<Cliente,Conta> que estão vinculados
//    public void excluirContaVinculada(int removerConta) {
//        Iterator<Map.Entry<Cliente, Conta>> it = contasPorCliente.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Cliente, Conta> entry = it.next();
//            if (entry.getValue().getNumero() == removerConta) {
//                it.remove();
//            }
//        }
//        System.out.println("Conta de número -> "+ removerConta+ "foi excluida com sucesso do cliente vinculado");
//    }
//
//    //Encontrei alguns padrões assim
////    boolean removido = contasPorCliente.entrySet().removeIf(
////            entry -> entry.getValue().getNumero() == removerConta);
////if (removido) {
////        System.out.println("msg");
////    } else {
////        System.out.println("msg");
////    }
//
//
//    //Exclui contas apenas da lista de contas
//    public void excluirConta(int removerConta) {
//        if (contas == null) {
//            System.out.println("Lista de contas não contem nada.");
//            return;
//        }
//        try {
//            contas.removeIf(conta -> conta != null && conta.getNumero() == removerConta);
//            System.out.println("Conta de número " + removerConta + " foi removida com sucesso.");
//        } catch (Exception e) {
//            System.err.println("Erro ao remover contas: " + e.getMessage());
//        }
//
//    }
//
//        public Conta buscaPorConta(int conta) {
//        for (Conta c : contas) {
//            if (c.getNumero() == conta) {
//                return c;
//            }
//        }
//        System.out.println("Conta não existe.");
//        return null;
//    }
//
//
//    public Cliente buscarClientePorCpf(String cpf) {
//        for (Cliente c : clientes) {
//            if (c.getCPF().equals(cpf)) {
//                return c;
//            }
//        }
//        System.out.println("CPF não existe na base.");
//        return null;
//    }
//}
