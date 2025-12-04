package main.java.V1.POO.Service;

import main.java.V1.POO.Model.Cliente;
import main.java.V1.POO.Model.Conta;

import java.util.*;

public class ConsultaService {
    private BancoService bancoService;

    //consulta para utilizar em usuários comuns
    public ConsultaService(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    public void imprimirClientes() {
        bancoService.listarClientes().forEach(c ->
                System.out.println("Cliente: " + c.getNome() + " | CPF: " + c.getCPF()));
    }

    public void imprimirContas() {
        bancoService.listarContas().forEach(ct ->
                System.out.println("Conta: " + ct.getNumero() + " | Saldo: " + ct.getSaldo()));
    }

    public List<String> top3Saldos() {
        List<Conta> copiaContas = new ArrayList<>(bancoService.listarContas());
        return copiaContas.stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .limit(3)
                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
                .toList();
    }


    public List<String> ordenaPorSaldo() {
        List<Conta> ordemSaldo = new ArrayList<>(bancoService.listarContas());
        return ordemSaldo.stream()
                .sorted(Comparator.comparing(Conta::getSaldo).reversed())
                .map(conta -> String.format("Conta %d - Saldo: %.2f", conta.getNumero(), conta.getSaldo()))
                .toList();
    }


    public List<Conta> buscarContasPorCpf(String cpf) {
        for (Map.Entry<Cliente, List<Conta>> entry : bancoService.listarContasClientes().entrySet()) {
            Cliente cliente = entry.getKey();
            if (cliente.getCPF().equals(cpf)) {
                return entry.getValue();
                 }
        }
        return Collections.emptyList();
    }

}
