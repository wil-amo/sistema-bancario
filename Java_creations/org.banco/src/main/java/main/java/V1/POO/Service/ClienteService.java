package main.java.V1.POO.Service;

import main.java.V1.POO.Model.Cliente;


public class ClienteService {
    private Cliente cliente;

    public ClienteService( Cliente cliente){
        this.cliente=cliente;
    }

    @Override
    public String toString() {
        return cliente != null ? cliente.toString() : "Cliente vazio";
    }



}
