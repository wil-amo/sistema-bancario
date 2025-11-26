package main.java.V1.POO.Model;
import java.util.*;

public class Banco {
    private String nome;
    private List<Cliente> clientes;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
        this.contasPorCliente = new HashMap<>();
    }

    public String getNome() { return nome; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Conta> getContas() { return contas; }

    // cliente pode ter várias contas
    private Map<Cliente, List<Conta>> contasPorCliente;
    public Map<Cliente, List<Conta>> getContasPorCliente() { return contasPorCliente; }

    // Vincula cliente e conta (agora adiciona na lista)
    public void vinculaContaECliente(Cliente cliente, Conta conta) {
                contasPorCliente
                        .computeIfAbsent(cliente, k -> new ArrayList<>())
                .add(conta);
            contas.add(conta);
    }


    // Exibe formatado todas as contas vinculadas
    public List<String> listarContasEClientes() {
        List<String> vinculados = new ArrayList<>();
        for (Map.Entry<Cliente, List<Conta>> entry : contasPorCliente.entrySet()) {
            Cliente cliente = entry.getKey();
            for (Conta conta : entry.getValue()) {
                String info = String.format("%nCliente: %s | Conta: %d | Agência: %s | Saldo: %.2f",
                        cliente.getNome(),
                        conta.getNumero(),
                        conta.getAgencia(),
                        conta.getSaldo());
                vinculados.add(info);
            }
        }
        return vinculados;
    }

    //    //Trabalha em conjunto com excluirConta
   // Exclui conta do map<Cliente,Conta> que estão vinculados
    // Exclui conta vinculada a um cliente
    public void excluirContaVinculada(int numeroConta) {
        for (List<Conta> lista : contasPorCliente.values()) {
            lista.removeIf(c -> c.getNumero() == numeroConta);
        }
            contas.removeIf(c -> c.getNumero() == numeroConta);
                    System.out.println("Conta de número -> " + numeroConta + " foi excluída com sucesso");
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void addConta(Conta conta) {
        contas.add(conta);
    }

    //Verificar alguns pontos
    public boolean removerConta(int numeroConta) {
        boolean removed = contas.removeIf(c -> c.getNumero() == numeroConta);
        contasPorCliente.values().forEach(lista -> lista.removeIf(c -> c.getNumero() == numeroConta));
        return removed;
    }
}

