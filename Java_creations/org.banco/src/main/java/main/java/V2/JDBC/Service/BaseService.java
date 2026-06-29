package main.java.V2.JDBC.Service;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseService {

    // protect - apenas services veêm
    protected void encerrarConexaoComRollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                System.err.println("Erro ao desfazer transação: " + e.getMessage());
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}
