package main.java.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {
        private static final String NOME = "Banco do Povo";

        private List<Conta> contas;
        Conta conta;
        public Conta getConta() {
            return conta;
        }


        public Banco() {
            this.contas = new ArrayList<>();
        }


        public void adicionarConta(Conta conta) {
            contas.add(conta);
        }
        public String listarContas() {
            StringBuilder sb = new StringBuilder("=== LISTA DE CONTAS TOTAIS DO BANCO ===\n");
            for (Conta conta : contas) {
                sb.append(conta.toString()).append("\n");
            }
            return sb.toString();
        }


        public void listarContasCorrente() {
            for (Conta conta : contas) {
                if (conta instanceof ContaCorrente) {
                    System.out.println(String.format("Agencia: %s" , conta.getAgencia()));
                    System.out.println(String.format("Cliente : %s", conta.getCliente().getNome()));
                    System.out.println(String.format("Número: %d" , conta.getNumero()));
                    System.out.println(String.format("Saldo: %.2f" , conta.getSaldo()));
                }
            }
        }

            public void listarContasPoupanca() {
                for (Conta conta : contas) {
                    if (conta instanceof ContaPoupanca) {
                        System.out.println(String.format("Agencia: %s" , conta.getAgencia()));
                        System.out.println(String.format("Cliente : %s", conta.getCliente().getNome()));
                        System.out.println(String.format("Número: %d" , conta.getNumero()));
                        System.out.println(String.format("Saldo: %.2f" , conta.getSaldo()));
                    }
                }
            }


        public String getNome() {
            return NOME;
        }

        //CLIENTES
        private List<Cliente> clientes = new ArrayList<>();

        public void adicionarCliente(Cliente cliente) {
            clientes.add(cliente);
        }
    public List<Cliente> getClientes() {
        return clientes;
    }

        //encontra (conta e cliente)
        private Map<Cliente, Conta> contasPorCliente = new HashMap<>();

        //vincula cliente e conta
        public void vincularConta(Cliente cliente, Conta conta) {
            contasPorCliente.put(cliente, conta);
        }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    }
