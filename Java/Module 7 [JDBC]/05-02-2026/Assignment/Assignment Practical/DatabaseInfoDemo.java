package com.JDBC;

/*Lab 7.2: Display database name, version, list of tables, and supported SQL features.*/

import java.sql.*;

public class DatabaseInfoDemo 
{
    public static void main(String[] args) 
    {
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "jdbc";
		String url = host + db;

        try 
        {
            // 1. Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // 2. Connect to database
            Connection con = DriverManager.getConnection(url, "root", "");

            // 3. Get database metadata
            DatabaseMetaData meta = con.getMetaData();

            // 4. Display database name and version
            System.out.println("Database Product Name: " + meta.getDatabaseProductName());
            System.out.println("Database Product Version: " + meta.getDatabaseProductVersion());
            System.out.println("Driver Name: " + meta.getDriverName());
            System.out.println("Driver Version: " + meta.getDriverVersion());
            System.out.println("Supports Transactions? " + meta.supportsTransactions());
            System.out.println("Supports Stored Procedures? " + meta.supportsStoredProcedures());
            System.out.println("Max Connections: " + meta.getMaxConnections());
            System.out.println("Max Columns in Table: " + meta.getMaxColumnsInTable());

            System.out.println("\nList of Tables:");

            // 5. List all tables in the database
            ResultSet rsTables = meta.getTables(null, null, "%", new String[] {"TABLE"});
            while(rsTables.next()) 
            {
                String tableName = rsTables.getString("TABLE_NAME");
                System.out.println(tableName);
            }
            rsTables.close();

            // 6. List some supported SQL features
            System.out.println("\nSupported SQL Features:");
            System.out.println("Supports ANSI92 Entry Level: " + meta.supportsANSI92EntryLevelSQL());
            System.out.println("Supports Outer Joins: " + meta.supportsOuterJoins());
            System.out.println("Supports Stored Functions Using Call Syntax: " + meta.supportsStoredFunctionsUsingCallSyntax());

            // Close connection
            con.close();

        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}