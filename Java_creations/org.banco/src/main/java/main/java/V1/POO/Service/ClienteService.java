package main.java.V1.POO.Service;

import main.java.V1.POO.Exception.InvalidClientException;
import main.java.V1.POO.Model.Banco;
import main.java.V1.POO.Model.Cliente;


public class ClienteService {
    private Cliente cliente;
    private Banco banco;

    public ClienteService( Banco banco){
        this.banco= banco;
    }

 public void criaCliente(Cliente cliente){
            banco.addCliente(cliente);
 }
    public boolean atualizaCliente(String cpf, Cliente novoCliente) {
        if (cpf != null && banco.getClientesPorCpf().containsKey(cpf)) {
            Cliente clienteAntigo = banco.getClientesPorCpf().get(cpf);
            novoCliente.setConta(clienteAntigo.getConta());
            banco.getClientesPorCpf().put(cpf, novoCliente);

            return true;
        }
        return false;
    }

    public Cliente getClientePorCpf(String cpf) {
        Cliente cliente = banco.getClientesPorCpf().get(cpf);
        if (cliente == null) {
            throw new InvalidClientException("Cliente não encontrado para o CPF: " + cpf);
        }
        return cliente;
    }

    //Méttodo auxiliar que trata getClientePorCpf
    public Cliente safeGetCliente(String cpf) {
        try {
            return this.getClientePorCpf(cpf);
        } catch (InvalidClientException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return cliente != null ? cliente.toString() : "Cliente vazio";
    }



}
