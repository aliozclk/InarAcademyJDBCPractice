package org.example;

import java.sql.*;

public class Question {
    public static void answer() throws SQLException {


        // 1- GET a connection DB
        String dbURL = "jdbc:postgresql://localhost:5432/exercises";
        String username="postgres";
        String password = "ali.1996";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        //2- Create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //3- execute a query
        String firstQuery = "SELECT TO_CHAR(joindate, 'Month') AS to_char, COUNT(*) \n" +
                "FROM cd.members\n" +
                "GROUP BY to_char\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1;\n";
        ResultSet resultSet = statement.executeQuery(firstQuery);

        //4- process the result
        int cout = 1;
        while (resultSet.next()){

            System.out.println(cout++  + ":"+resultSet.getString("to_char") + ",,,,,,,,,,,count : " + resultSet.getString("count"));
        }
    }
}
