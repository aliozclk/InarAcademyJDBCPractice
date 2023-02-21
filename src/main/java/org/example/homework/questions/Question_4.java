package org.example.homework.questions;

import java.sql.*;

public class Question_4 {
    public static void main(String[] args) throws SQLException {


        // 1- GET a connection DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username="postgres";
        String password = "password";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        //2- Create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //3- execute a query
        String firstQuery = "SELECT c.country,SUM(p.amount) AS total FROM payment p\n" +
                "INNER JOIN customer cu ON p.customer_id=cu.customer_id\n" +
                "INNER JOIN address a ON a.address_id = cu.address_id\n" +
                "INNER JOIN city ct ON ct.city_id = a.city_id\n" +
                "INNER JOIN country c ON ct.country_id = c.country_id \n" +
                "GROUP BY c.country\n" +
                "HAVING SUM(p.amount) > 800\n" +
                "ORDER BY total DESC";
        ResultSet resultSet = statement.executeQuery(firstQuery);

        //4- process the result
        int count = 1;
        while (resultSet.next()){

            System.out.println(count++  + ":"+resultSet.getString("country") + ",total payment : " + resultSet.getString("total"));
        }
    }

}
