package main.java.V1.POO.Service;
import main.java.V1.POO.Model.*;
import java.util.*;


public class BancoService {

        private Banco banco;

        public BancoService(Banco banco) {
            this.banco = banco;
        }

    public List<String> mostrarContasDoCliente(String cpf) {
        List<Conta> contas = banco.listarContasPorCliente(cpf);
        List<String> resultado = new ArrayList<>();
        for (Conta conta : contas) {
            resultado.add(String.format("Cliente: %s | Conta: %d | Agência: %d | Saldo: %.2f",
                    conta.getCliente().getNome(),
                    conta.getNumero(),
                    conta.getAgencia(),
                    conta.getSaldo()));
        }
        System.out.println("\nAs contas do CPF digitado são:");
        return resultado;
    }



    public void mostrarContasEClientes() {
        Map<Cliente, List<Conta>> dados = banco.listarContasEClientes();
        System.out.println("\nListando clientes e contas atuais do banco: ");
        for (Map.Entry<Cliente, List<Conta>> entry : dados.entrySet()) {
            Cliente cliente = entry.getKey();
            for (Conta conta : entry.getValue()) {
                System.out.printf("Cliente: %s | CPF: %s | Conta: %d | Agência: %d | Saldo: %.2f%n",
                        cliente.getNome(),
                        cliente.getCPF(),
                        conta.getNumero(),
                        conta.getAgencia(),
                        conta.getSaldo());
            }
        }
    }



    public void excluirConta(int numeroConta){
            banco.excluiConta(numeroConta);
    }

}
