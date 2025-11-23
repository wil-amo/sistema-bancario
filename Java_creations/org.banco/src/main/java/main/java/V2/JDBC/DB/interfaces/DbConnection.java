package main.java.V2.JDBC.DB.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

 public  interface DbConnection {
    Connection getConnection() throws SQLException;
}

