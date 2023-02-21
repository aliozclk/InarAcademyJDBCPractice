package org.example.homework;

import lombok.Data;

import java.sql.*;

@Data
public class ConnectionOfExercises {
    String dbURL = "jdbc:postgresql://localhost:5432/exercises";
    String username="postgres";
    String password = "password";

    Connection connection;
    Statement statement;

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
