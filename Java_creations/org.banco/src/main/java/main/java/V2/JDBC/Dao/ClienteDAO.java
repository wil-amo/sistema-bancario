package main.java.V2.JDBC.Dao;

import main.java.V2.JDBC.DB.interfaces.DbConnection;
import main.java.V2.JDBC.Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    //Previne alteração de DB
    private final DbConnection dbConnection;

    public ClienteDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void insertCliente(Cliente c) {
        String insertSql = "INSERT INTO clientes (cpf, name, login, email) VALUES (?, ?, ?, ?)";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSql)) {

            ps.setString(1, c.getCPF());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getLogin());
            ps.setString(4, c.getEmail());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage());
        }
    }
}