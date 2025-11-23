package main.java.V2.JDBC.DB;

import main.java.V2.JDBC.DB.interfaces.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DbConnection {
    //Sem acesso para setar timezone no server
    private static final String url = "jdbc:mysql://localhost:3306/sistema-bancario?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String user = "root";
    private static final String pass = "12345678";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }


}