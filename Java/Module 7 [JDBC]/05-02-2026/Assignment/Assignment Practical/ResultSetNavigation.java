package com.JDBC;

/*Lab 6.2: Demonstrate how to navigate through the ResultSet using methods like next(),
previous(), etc.*/

import java.sql.*;

public class ResultSetNavigation 
{
    public static void main(String[] args) 
    {
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "jdbc";
		String url = host + db;

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");

            // Create scrollable ResultSet
            Statement stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs = stmt.executeQuery("SELECT emp_id, first_name, last_name FROM employees");

            // 1️ Move forward using next()
            System.out.println("Forward iteration using next():");
            while(rs.next()) 
            {
                System.out.println(rs.getInt("emp_id") + " - " +
                                   rs.getString("first_name") + " " +
                                   rs.getString("last_name"));
            }

            // 2️ Move backward using previous()
            System.out.println("\nBackward iteration using previous():");
            while(rs.previous()) 
            {
                System.out.println(rs.getInt("emp_id") + " - " +
                                   rs.getString("first_name") + " " +
                                   rs.getString("last_name"));
            }

            // 3️ Move to first row
            rs.first();
            System.out.println("\nFirst row: " + rs.getInt("emp_id") + " - " +
                               rs.getString("first_name") + " " +
                               rs.getString("last_name"));

            // 4️ Move to last row
            rs.last();
            System.out.println("Last row: " + rs.getInt("emp_id") + " - " +
                               rs.getString("first_name") + " " +
                               rs.getString("last_name"));

            // 5️ Move to absolute row (e.g., row 2)
            rs.absolute(2);
            System.out.println("Absolute row 2: " + rs.getInt("emp_id") + " - " +
                               rs.getString("first_name") + " " +
                               rs.getString("last_name"));

            // 6️ Move relative (e.g., go one row back)
            rs.relative(-1);
            System.out.println("Relative -1 row: " + rs.getInt("emp_id") + " - " +
                               rs.getString("first_name") + " " +
                               rs.getString("last_name"));

            rs.close();
            stmt.close();
            con.close();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}