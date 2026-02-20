package main.java.V2.JDBC.Infra;

import main.java.V2.JDBC.Config.interfaces.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DbHealthCheck {

    public static void check(DbConnection db) {
        try (Connection connection = db.getConnection()) {

            if (connection == null || connection.isClosed()) {
                throw new SQLException("Conexão retornada como nula ou fechada.");
            }

            System.out.println("✅ Banco de Dados: UP");

        } catch (SQLException e) {
            throw new RuntimeException("Falha crítica no Banco de Dados: " + e.getMessage(), e);
            //implementar log
        }
    }
}
