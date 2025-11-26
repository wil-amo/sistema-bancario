package main.java.V1.POO.Service;

import main.java.V1.POO.Model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultaService {
    private Banco banco;

    public ConsultaService(Banco banco) {
        this.banco = banco;
    }
    public void imprimirClientes() {
        banco.getClientes().forEach(c ->
                System.out.println("Cliente: " + c.getNome() + " | CPF: " + c.getCPF()));
    }

    public void imprimirContas() {
        banco.getContas().forEach(ct ->
                System.out.println("Conta: " + ct.getNumero() + " | Saldo: " + ct.getSaldo()));
    }
    // Top 3 saldos
    public List<String> top3Saldos() {
        List<Conta> copiaContas = new ArrayList<>(banco.getContas());
        return copiaContas.stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .limit(3)
                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
                .toList();
    }

    // Ordenar por saldo
    public List<String> ordenaPorSaldo() {
        List<Conta> ordemSaldo = new ArrayList<>(banco.getContas());
        return ordemSaldo.stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
                .toList();
    }


    // Consultar funcionamento
    public List<String> contasPorCliente(Map<Cliente, List<Conta>> contasPorCliente) {
        return contasPorCliente.entrySet().stream()
                .map(entry -> String.format("Cliente: %s | Contas: %s",
                        entry.getKey().getNome(),
                        entry.getValue().stream()
                                .map(c -> String.valueOf(c.getNumero()))
                                .collect(Collectors.joining(", "))))
                .collect(Collectors.toList());
    }
    // Buscar conta por número
    public Conta buscaPorConta(int numeroConta) {
        for (Conta c : banco.getContas()) {
            if (c.getNumero() == numeroConta) {
                return c;
            }
        }
        System.out.println("Conta não existe.");
        return null;
    }

    // Buscar cliente por CPF
    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : banco.getClientes()) {
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        System.out.println("CPF não existe na base.");
        return null;
    }

}
