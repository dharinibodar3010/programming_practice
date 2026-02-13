package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

/*Lab 1.1: Write a simple Java program to connect to a MySQL database using JDBC.*/

public class JDBCExample 
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
			
			System.out.println("Database connected successfully!");
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
