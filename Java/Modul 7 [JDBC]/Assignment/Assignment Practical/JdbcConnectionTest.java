package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

/*Lab 3.1: Write a Java program to establish a connection to a database and print a
confirmation message upon successful connection.*/

public class JdbcConnectionTest 
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
			
			if (con != null) 
			{
				System.out.println("Database connected successfully.");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
