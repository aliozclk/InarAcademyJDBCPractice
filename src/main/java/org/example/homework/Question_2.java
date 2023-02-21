package org.example.homework;

import java.sql.*;

public class Question_2 {
    public static void main(String[] args) throws SQLException {


        // 1- GET a connection DB
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username="postgres";
        String password = "ali.1996";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        //2- Create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //3- execute a query
        String firstQuery = " SELECT CONCAT(s.first_name,' ',s.last_name) AS name , s.username , s.password  FROM staff s\n" +
                "        INNER JOIN store st ON st.manager_staff_id = s.staff_id\n" +
                "        INNER JOIN inventory i ON i.store_id = st.store_id\n" +
                "        INNER JOIN film f ON f.film_id = i.film_id\n" +
                "        WHERE f.title LIKE 'Glass Dying'\n" +
                "        GROUP BY name,s.username,s.password";
        ResultSet resultSet = statement.executeQuery(firstQuery);

        //4- process the result
        int count = 1;
        while (resultSet.next()){

            System.out.println(count++  + ":"+resultSet.getString("name") +  " , username : " +
                    resultSet.getString("username") + "  , password : "
                    + resultSet.getString("password"));
        }
    }
}
