package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*Lab 6.1: Write a program that executes a SELECT query and processes the 
 		   ResultSet to display records from the database.*/

public class SelectRecordsExample 
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
			
			String sql = "select * from student";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet set = ps.executeQuery();
			
			while (set.next())
			{
				int id = set.getInt("id");
				String name = set.getString("name");
				int marks = set.getInt("marks");
				
				System.out.println(id + " " + name + " " + marks);
			}
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
