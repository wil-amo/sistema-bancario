package main.java.V2.JDBC.Infra;

import main.java.V2.JDBC.DB.MySQLConnection;
import main.java.V2.JDBC.DB.interfaces.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DbHealthCheck {

    public static void healthCheckMySql() {
        DbConnection db = new MySQLConnection();
        try (Connection connection = db.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexão up!");
            } else {
                System.out.println("Não foi possível conectar ao db.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao db -> sistema-bancario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
