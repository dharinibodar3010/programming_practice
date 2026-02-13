package com.JDBC;

import java.sql.*;

/*Lab 11.2: Write a Java program that uses CallableStatement to call this stored procedure.*/

public class CallProcedureExample 
{
    public static void main(String[] args) 
    {
        // Database connection details
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "jdbc";
		String url = host + db;
        
		Connection con = null;
        CallableStatement cstmt = null;

        try 
        {
            // Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to database
            con = DriverManager.getConnection(url, "root", "");

            // Prepare the callable statement
            // {CALL ProcedureName(IN param, OUT param)}
            cstmt = con.prepareCall("{CALL GetEmployeeFullName(?, ?)}");

            // Set IN parameter (employee ID)
            cstmt.setInt(1, 1); // example: emp_id = 1

            // Register OUT parameter (full name)
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // Execute the procedure
            cstmt.execute();

            // Get the value of OUT parameter
            String fullName = cstmt.getString(2);

            System.out.println("Employee Full Name: " + fullName);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
}