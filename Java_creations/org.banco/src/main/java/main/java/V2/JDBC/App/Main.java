package main.java.V2.JDBC.App;

import javax.swing.*;

import main.java.V2.JDBC.Config.MySQLConnection;
import main.java.V2.JDBC.Config.interfaces.DbConnection;
import main.java.V2.JDBC.DAO.ClienteDAO;
import main.java.V2.JDBC.DAO.ContaDAO;
import main.java.V2.JDBC.Infra.DbHealthCheck;
import main.java.V2.JDBC.Service.ServiceCliente;
import main.java.V2.JDBC.Service.ServiceConta;
import main.java.V2.JDBC.View.UI;


public class Main {

    public static void main(String[] args) {
        // 1. Inicia a infraestrutura com interface
        DbConnection connection = new MySQLConnection();

        try {
            DbHealthCheck.check(connection);
            // implementar -> armazenar no .log
            System.out.println("✅ Conexão com o banco estabelecida com sucesso.");

            // 2. DAOS
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            ContaDAO contaDAO = new ContaDAO(connection, clienteDAO);

            // 4. Services
            ServiceCliente serviceCliente = new ServiceCliente(clienteDAO);
            ServiceConta serviceConta = new ServiceConta(connection, clienteDAO, contaDAO);

            // 5. Inicia a UI se banco ok
            SwingUtilities.invokeLater(() -> {
                UI ui = new UI(serviceCliente, serviceConta);
                ui.criaUI();
            });

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível conectar ao banco de dados.\n" +
                            "Erro: " + e.getMessage(),
                    "Erro de Sistema",
                    JOptionPane.ERROR_MESSAGE);

            //Encerra JVM se erro no DB!
            System.exit(1);
        }
    }
}

