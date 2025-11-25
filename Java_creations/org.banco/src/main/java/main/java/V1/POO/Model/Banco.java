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
    }

    public String getNome() { return nome; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Conta> getContas() { return contas; }

    private Map<Cliente, Conta> contasPorCliente = new HashMap<>();

    public Map<Cliente, Conta> getContasPorCliente() {
        return contasPorCliente;
    }

    //    //vincula cliente e conta
    public void vinculaContaECliente(Cliente cliente, Conta conta) {
        contasPorCliente.put(cliente, conta);
    }

    //Apenas exibe formatado
    public List<String> listarContasEClientes() {
        List<String> vinculados = new ArrayList<>();
        for (Map.Entry<Cliente, Conta> entry : contasPorCliente.entrySet()) {
            Cliente cliente = entry.getKey();
            Conta conta = entry.getValue();
            String info = String.format("%nCliente: %s | Conta: %d | Agência: %s | Saldo: %.2f",
                    cliente.getNome(),
                    conta.getNumero(),
                    conta.getAgencia(),
                    conta.getSaldo());
            vinculados.add(info);
        }
        return vinculados;
    }

    //    //Trabalha em conjunto com excluirConta
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


    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }


    public void addConta(Conta conta) {
        contas.add(conta);

    }

    public void removeConta(int numeroConta) {
        contas.removeIf(c -> c.getNumero() == numeroConta); // remove usando
        contasPorCliente.entrySet().removeIf(entry -> entry.getValue().getNumero() == numeroConta);
    }

    //implementar, remoção com validação
//    public boolean removeConta(int numeroConta) {
//        return contas.removeIf(c -> c.getNumero() == numeroConta);
//    }


}

