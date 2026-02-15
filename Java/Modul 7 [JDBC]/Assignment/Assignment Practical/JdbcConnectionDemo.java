package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

/*Lab 1.2: Demonstrate the process of loading a JDBC driver and establishing a connection.*/

public class JdbcConnectionDemo 
{
	public static void main(String[] args) 
	{
		try 
		{
//			step 1: load JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
//			step 2: establish connection
			String host = "jdbc:mysql://localhost:3306/";
			String db = "jdbc";
			String url = host + db;
			
			Connection con = DriverManager.getConnection(url, "root", "");
			
//			step 3: verify connection
			System.out.println("Database connected successfully");
			
//			step 4: close connection
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
