package main.java.V1.POO.Model;

import main.java.V1.POO.Exception.InvalidContaException;
import main.java.V1.POO.Model.interfaces.Iconta;

import javax.swing.*;
import java.util.*;

public class Banco {
    private String nome;
    private List<Conta> contas;
    private Map<String, Cliente> clientesPorCpf;
    private Map<String, List<Conta>> contasPorCpf;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
        this.clientesPorCpf = new HashMap<>();
        this.contasPorCpf = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public Map<String, Cliente> getClientesPorCpf() {
        return clientesPorCpf;
    }

    public Map<String, List<Conta>> getContasPorCpf() {
        return contasPorCpf;
    }


    public void addCliente(Cliente cliente) {
        String cpf = cliente.getCPF();
        if (clientesPorCpf.containsKey(cpf)) {
            System.out.println("Já existe cliente com CPF: " + cpf);
        } else {
            clientesPorCpf.put(cpf, cliente);
            System.out.println("Cliente adicionado: " + cliente.getNome());
        }
    }

    public void addConta(String cpf, Iconta conta) {
        if (!clientesPorCpf.containsKey(cpf)) {
            throw new InvalidContaException("Cliente com CPF " + cpf + " não encontrado.");
        }
        contasPorCpf.computeIfAbsent(cpf, k -> new ArrayList<>()).add(((Conta) conta));
        contas.add(((Conta) conta));
        System.out.println("Conta adicionada para cliente: " + clientesPorCpf.get(cpf).getNome());
    }

    //Retornam objetos

    public List<Conta> listarContasPorCliente(String cpf) {
        return contasPorCpf.getOrDefault(cpf, Collections.emptyList());
    }

    public Map<Cliente, List<Conta>> listarContasEClientes() {
        Map<Cliente, List<Conta>> resultado = new HashMap<>();
        for (Map.Entry<String, List<Conta>> entry : contasPorCpf.entrySet()) {
            Cliente cliente = clientesPorCpf.get(entry.getKey());
            resultado.put(cliente, entry.getValue());
        }
        return resultado;
    }

    // Exclui conta pelo número
    public void excluiConta(int numeroConta) {
        for (List<Conta> lista : contasPorCpf.values()) {
            lista.removeIf(c -> c.getNumero() == numeroConta);
        }
        contas.removeIf(c -> c.getNumero() == numeroConta);
        System.out.println("Conta de número -> " + numeroConta + " foi excluída com sucesso");
    }
}



