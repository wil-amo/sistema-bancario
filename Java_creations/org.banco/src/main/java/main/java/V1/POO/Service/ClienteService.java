package main.java.V1.POO.Service;

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
            banco.getClientesPorCpf().put(cpf, novoCliente);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return cliente != null ? cliente.toString() : "Cliente vazio";
    }



}
