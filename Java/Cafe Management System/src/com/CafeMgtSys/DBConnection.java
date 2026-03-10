package com.CafeMgtSys;
import java.sql.*;

public class DBConnection {
    // Method to establish connection with MySQL Database
    public static Connection getConnection() throws Exception {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.jdbc.Driver");
        // Return connection object using DB URL, Username, and Password
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe", "root", "");
    }
}
