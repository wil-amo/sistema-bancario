package main.java.V1.POO.Service;

import main.java.V1.POO.Model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultaService {
    private Banco banco;

    public ConsultaService(Banco banco) {
        this.banco = banco;
    }

    // Top 3 maiores saldos
    public List<String> top3Saldos() {
        return banco.getContas().stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .limit(3)
                .map(c -> String.format("Conta %d | Saldo: %.2f", c.getNumero(), c.getSaldo()))
                .collect(Collectors.toList());
    }

    // Ordena todas as contas por saldo decrescente
    public List<String> ordenarPorSaldo() {
        return banco.getContas().stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .map(c -> String.format("Conta %d | Saldo: %.2f", c.getNumero(), c.getSaldo()))
                .collect(Collectors.toList());
    }

    // Consulta contas vinculadas a clientes
    public List<String> contasPorCliente(Map<Cliente, List<Conta>> contasPorCliente) {
        return contasPorCliente.entrySet().stream()
                .map(entry -> String.format("Cliente: %s | Contas: %s",
                        entry.getKey().getNome(),
                        entry.getValue().stream()
                                .map(c -> String.valueOf(c.getNumero()))
                                .collect(Collectors.joining(", "))))
                .collect(Collectors.toList());
    }
}
