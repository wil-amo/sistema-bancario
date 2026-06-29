package main.java.V2.JDBC.Service;

import main.java.V2.JDBC.DAO.ClienteDAO;
import main.java.V2.JDBC.Model.Cliente;
import java.util.List;

public class ServiceCliente {
    private final ClienteDAO clienteDAO;


    public ServiceCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.readAllClientes();
    }

    public Cliente readClienteporCPF(String cpf){
       return clienteDAO.readClienteporCPF(cpf);
    }

    public void updateCliente(Cliente c){
         clienteDAO.updateCliente(c);
    }

    public void createCliente (Cliente c){
        clienteDAO.createCliente(c);
    }








}
