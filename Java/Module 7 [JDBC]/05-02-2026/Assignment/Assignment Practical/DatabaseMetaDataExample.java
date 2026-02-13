package com.JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

/*Lab 7.1: Write a program that retrieves and displays metadata information about your
		   database using DatabaseMetaData.*/

public class DatabaseMetaDataExample 
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
			
			DatabaseMetaData dbmd = con.getMetaData();
			
			System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
			System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("Database URL: " + dbmd.getURL());
			System.out.println("User Name: " + dbmd.getUserName());
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
