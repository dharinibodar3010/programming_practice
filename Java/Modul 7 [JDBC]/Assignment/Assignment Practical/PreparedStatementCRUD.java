package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*Lab 4.2: Modify the program to use PreparedStatement for parameterized queries.*/

public class PreparedStatementCRUD {

    public static void main(String[] args) 
    {

    	String host = "jdbc:mysql://localhost:3306/";
		String db = "jdbc";
		String url = host + db;

        try 
        {
            // 1. Load JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            // 2. Create Connection
            Connection con = DriverManager.getConnection(url, "root", "");

            // ---------------- INSERT ----------------
            String insertSQL = "INSERT INTO student (id, name, marks) VALUES (?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(insertSQL);

            psInsert.setInt(1, 1);
            psInsert.setString(2, "Radha");
            psInsert.setInt(3, 88);

            psInsert.executeUpdate();
            System.out.println("Record Inserted");

            // ---------------- SELECT ----------------
            String selectSQL = "SELECT * FROM student";
            PreparedStatement psSelect = con.prepareStatement(selectSQL);

            ResultSet rs = psSelect.executeQuery();
            
            System.out.println("\nID  Name   Marks");
            
            while (rs.next()) 
            {
                System.out.println(
                    rs.getInt("id") + "  " +
                    rs.getString("name") + "  " +
                    rs.getInt("marks")
                );
            }

            // ---------------- UPDATE ----------------
            String updateSQL = "UPDATE student SET marks = ? WHERE id = ?";
                
            PreparedStatement psUpdate = con.prepareStatement(updateSQL);

            psUpdate.setInt(1, 90);
            psUpdate.setInt(2, 1);

            psUpdate.executeUpdate();
            System.out.println("\nRecord Updated");

            // ---------------- DELETE ----------------
            String deleteSQL = "DELETE FROM student WHERE id = ?";
                
            PreparedStatement psDelete = con.prepareStatement(deleteSQL);

            psDelete.setInt(1, 1);
            psDelete.executeUpdate();
            System.out.println("Record Deleted");

            // 3. Close connection
            con.close();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}