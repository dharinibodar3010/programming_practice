package com.JDBC;

/*Lab 11.3: Demonstrate how to pass IN parameters and retrieve OUT parameters.*/

import java.sql.*;

public class CallableDemo 
{
    public static void main(String[] args) 
    {
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "jdbc";
		String url = host + db;
		
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            // 1. Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // 2. Connect to database
            con = DriverManager.getConnection(url, "root", "");

            // 3. Prepare callable statement
            // {CALL ProcedureName(IN param, OUT param)}
            cstmt = con.prepareCall("{CALL GetEmployeeFullName(?, ?)}");

            // 4. Set IN parameter
            cstmt.setInt(1, 2); // Example: emp_id = 2

            // 5. Register OUT parameter
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // 6. Execute the procedure
            cstmt.execute();

            // 7. Retrieve OUT parameter
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