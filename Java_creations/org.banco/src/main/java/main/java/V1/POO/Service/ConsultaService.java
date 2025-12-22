package main.java.V1.POO.Service;

import main.java.V1.POO.Model.Cliente;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaService {
    private BancoService bancoService;

    public ConsultaService(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    //Altera-los para void
    public List<String> clientesOrdenadoPorCPF() {
        List<String> resultadoFormatado = bancoService.getClientesPorCpf().values().stream()
                .sorted(Comparator.comparing(Cliente::getCPF))
                .map(s -> String.format("Nome: %s | CPF: %s | Email: %s | Login: %s",
                        s.getNome(), s.getCPF(), s.getEmail(), s.getLogin()))
                .toList();

        System.out.println("\nClientes por CPF:");
        resultadoFormatado.forEach(System.out::println);

        return resultadoFormatado;
    }


    public List<String> clientesOrdenadosPornome() {
        List<String> resultadoFormatado = bancoService.getClientesPorCpf().values().stream()
                .sorted(Comparator.comparing(Cliente::getNome))
                .map(s -> String.format("Nome: %s | CPF: %s | Email: %s | Login: %s",
                        s.getNome(), s.getCPF(), s.getEmail(), s.getLogin()))
                .toList();

        System.out.println("\nClientes por Nome:");
        resultadoFormatado.forEach(System.out::println);

        return resultadoFormatado;
    }

    public List<String> clientesOrdenadosPorSaldo() {
        List<String> clientesOrdenadosPorSaldo = bancoService.getClientesPorCpf().values().stream()
                .filter(p -> p.getConta() != null)
                .sorted(Comparator.comparing(c -> (c.getConta().getSaldo())))
                .map(p-> String.format("Cliente: %s | Saldo: %.2f", p.getNome(),p.getConta().getSaldo())).toList();

        System.out.println("\nClientes ordenados por saldo");
        clientesOrdenadosPorSaldo.forEach(System.out::println);
        return clientesOrdenadosPorSaldo;

    }



    public List<String> exibirContasPorCPF() {
        List<String> contasPorCPF = bancoService.getContasPorCpf().entrySet().stream()
                .map(e -> String.format("CPF: %s | Contas: %s",
                        e.getKey(),
                        e.getValue().stream()
                                .map(c -> String.format("[Agência: %s, Número: %s, Saldo: %.2f]",
                                        c.getAgencia(), c.getNumero(), c.getSaldo()))
                                .collect(Collectors.joining(", "))))
                .toList();

        System.out.println("\nExibindo contas por CPF");
        contasPorCPF.forEach(System.out::println);
        return contasPorCPF;
    }


    public List<String> buscaClientePorNome(String nome){
        List<String> nomeCliente = bancoService.getClientesPorCpf().values().stream()
                .filter(cliente -> cliente.getNome().equals(nome))
                //String format?
                .map(s-> "Nome:" + s.getNome()+" |Conta:"+ s.getConta().getNumero()+ " |Saldo:"+s.getConta().getSaldo()).toList();
        return nomeCliente.isEmpty() ? null : nomeCliente;
    }

    public List<String> clientesSemConta() {
        List<String> resultado = bancoService.getClientesPorCpf().values().stream()
                .filter(c -> c.getConta() == null)
                .sorted(Comparator.comparing(Cliente::getNome))
                .map(c -> String.format("Cliente: %s | CPF: %s | Email: %s", c.getNome(), c.getCPF(), c.getEmail()))
                .toList();

        System.out.println("\nClientes sem conta:");
        resultado.forEach(System.out::println);
        return resultado;
    }
}