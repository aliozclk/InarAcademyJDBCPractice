package org.example.homework;

import lombok.Data;

import java.sql.*;

public class ConnectionOfDvdrental {

    static String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
    static String username = "postgres";
    static String password = "ali.1996";
    static Connection connection  ;
    static Statement statement ;

    public void setConnection() {
        try {
            connection = connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStatement() {

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
