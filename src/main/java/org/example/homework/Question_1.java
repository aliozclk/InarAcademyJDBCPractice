package org.example.homework;

import java.sql.*;

public class Question_1{


    public static void main(String[] args) throws SQLException {

        // 1- GET a connection DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username="postgres";
        String password = "ali.1996";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        //2- Create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //3- execute a query
        String firstQuery = "SELECT CONCAT(c.first_name,' ',c.last_name) AS name , r.rental_date AS date FROM customer c\n" +
                "    INNER JOIN rental r ON c.customer_id = r.customer_id\n" +
                "    INNER JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
                "    INNER JOIN film f ON f.film_id = i.film_id\n" +
                "    INNER JOIN film_actor fa ON fa.film_id = f.film_id\n" +
                "    INNER JOIN actor a ON a.actor_id = fa.actor_id\n" +
                "    WHERE a.first_name LIKE 'Ed' AND a.last_name LIKE 'Chase'\n" +
                "    GROUP BY name,date\n" +
                "    ORDER BY r.rental_date DESC\n" +
                "    LIMIT 3;";
        ResultSet resultSet = statement.executeQuery(firstQuery);

        //4- process the result
        int count = 1;
        while (resultSet.next()){

            System.out.println(count++  + ":"+resultSet.getString("name") +  "   -  date : " + resultSet.getString("date"));
        }
    }


}