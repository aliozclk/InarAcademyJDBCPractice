package org.example.homework.questions;

import java.sql.*;

public class Question_5 {
    public static void main(String[] args) throws SQLException {


        // 1- GET a connection DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username="postgres";
        String password = "password";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        //2- Create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //3- execute a query
        String firstQuery = "SELECT SUM(f.rental_rate) AS total_revenue FROM film f\n" +
                "INNER JOIN inventory i ON i.film_id = f.film_id\n" +
                "WHERE i.store_id = 2";
        ResultSet resultSet = statement.executeQuery(firstQuery);

        //4- process the result
        int count = 1;
        while (resultSet.next()){

            System.out.println(count++  + ":"  + resultSet.getString("total_revenue"));
        }
    }
}
