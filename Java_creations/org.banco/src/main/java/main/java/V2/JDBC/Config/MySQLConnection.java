package main.java.V2.JDBC.Config;

import main.java.V2.JDBC.Config.interfaces.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DbConnection {

        //criadas em ambiente da app
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String dbUrl = (URL != null) ? URL : "jdbc:mysql://localhost:3306/sistema-bancario?useSSL=false&serverTimezone=America/Sao_Paulo";
        return DriverManager.getConnection(dbUrl, USER, PASS);
    }


}