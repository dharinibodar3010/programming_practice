package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
    // Public static connection for all DAO classes
    public static Connection con = null;

    // Static block ensures connection is ready when class loads
    static 
    {
        try 
        {
            // 1️ Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver"); // MySQL 8 driver

            // 2️ Connect to database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
                    
            System.out.println("Database connected successfully!");
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } 
        catch (SQLException e) 
        {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // Optional helper method to get connection (alternative to static con)
    public static Connection getConnection() 
    {
        return con;
    }
}