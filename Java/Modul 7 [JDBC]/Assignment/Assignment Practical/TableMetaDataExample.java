package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/*Lab 8.2: Use a SELECT query to display this metadata for a specific table.*/

public class TableMetaDataExample 
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
			
			ResultSetMetaData rsmd = set.getMetaData();
			
			String tableName = rsmd.getTableName(1);
			int columnCount = rsmd.getColumnCount();
			
			System.out.println("Table Name: " + tableName );
			System.out.println("Total Columns: " + columnCount);
			
			for (int i = 1; i <= columnCount; i++) 
			{
				System.out.println("Column " + i + " Name : " + rsmd.getColumnName(i) + ", Type: " + rsmd.getColumnTypeName(i));
			}
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
