package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*Lab 9.1 & 2: 
o Write SQL queries for:
  Inserting a record into a table.
  Updating specific fields of a record.
  Selecting records based on certain conditions.
  Deleting specific records.
o Implement these queries in Java using JDBC.*/

public class JDBC_CRUD_Example 
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
			
//			insert
			String insertsql = "insert into student (name, marks) values (?, ?)";
			PreparedStatement ps1 = con.prepareStatement(insertsql);
			
			ps1.setString(1, "Radha");
			ps1.setInt(2, 95);			
			ps1.executeUpdate();
			
			System.out.println("Record Inserted");
			
//			update
			String updatesql = "update student set marks = ? where id = ?";
			PreparedStatement ps2 = con.prepareStatement(updatesql);
			
			ps2.setInt(1, 90);
			ps2.setInt(2, 1);
			ps2.executeUpdate();
			
			System.out.println("Record Updated");
		
//			select
			String selectsql = "select * from student where marks > ?";
			
			PreparedStatement ps3 = con.prepareStatement(selectsql);
			
			ps3.setInt(1, 75);
			
			ResultSet set = ps3.executeQuery();
			
			while (set.next())
			{
				int id = set.getInt("id");
				String name = set.getString("name");
				int marks = set.getInt("marks");
				
				System.out.println(id + " " + name + " " + marks);
			}
 		
//			delete
			String deletesql = "delete from student where id = ?";
			
			PreparedStatement ps4 = con.prepareStatement(deletesql);
			
			ps4.setInt(1, 1);
			ps4.executeUpdate();
			
			System.out.println("Record Deleted");
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
