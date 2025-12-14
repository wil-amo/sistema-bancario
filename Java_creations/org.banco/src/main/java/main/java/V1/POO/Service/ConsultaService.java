package main.java.V1.POO.Service;

import main.java.V1.POO.Model.Cliente;

import java.util.Comparator;
import java.util.List;

public class ConsultaService {
    private BancoService bancoService;

    public ConsultaService(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    public List<String> clientesOrdenadoPorCPF() {
        List<String> resultadoFormatado = bancoService.getClientesPorCpf().values().stream()
                .sorted(Comparator.comparing(Cliente::getCPF))
                    .map(s -> String.format("Nome: %s | CPF: %s | Email: %s | Login: %s",
                        s.getNome(), s.getCPF(), s.getEmail(), s.getLogin()))
                            .toList();

        System.out.println("\nClientes:");
        resultadoFormatado.forEach(System.out::println);

        return resultadoFormatado;
    }


    public List<String> clientesOrdenadoPornome() {
        List<String> resultadoFormatado = bancoService.getClientesPorCpf().values().stream()
                .sorted(Comparator.comparing(Cliente::getNome))
                    .map(s -> String.format("Nome: %s | CPF: %s | Email: %s | Login: %s",
                        s.getNome(), s.getCPF(), s.getEmail(), s.getLogin()))
                            .toList();

        System.out.println("\nClientes:");
        resultadoFormatado.forEach(System.out::println);

        return resultadoFormatado;
    }


//    public List<String> clientesOrdenadoPorSaldo() {
//        List<String> resultadoFormatado = bancoService.getClientesPorCpf()().stream()
//                .sorted(Comparator.comparing(Cliente::getCPF))
//                .map(s -> String.format("Nome: %s | CPF: %s | Email: %s | Login: %s",
//                        s.getNome(), s.getCPF(), s.getEmail(), s.getLogin()))
//                .toList();
//
//        System.out.println("\nClientes:");
//        resultadoFormatado.forEach(System.out::println);
//
//        return resultadoFormatado;
//    }


}
