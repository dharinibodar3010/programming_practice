package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*Lab 4.1: Create a program that inserts, updates, selects, and deletes data using Statement.*/

public class StatementCRUDExample 
{
	public static void main(String[] args) 
	{
		String host = "jdbc:mysql://localhost:3306/";
		String db = "jdbc";
		String url = host + db;
		
		try 
		{
//			load driver
			Class.forName("com.mysql.jdbc.Driver");
			
//			create connection
			Connection con = DriverManager.getConnection(url, "root", "");
			
//			create statement
			Statement stmt = con.createStatement();
			
//			insert
			String insertSQL = "insert into student(name, marks) values ('Dharini', 85)"; 
			stmt.executeUpdate(insertSQL);
			
			System.out.println("Record Inserted");
			
//			select
			String selectSQL = "select * from student";
			ResultSet set = stmt.executeQuery(selectSQL);
			
			System.out.println("ID NAME MARKS");
			
			while (set.next())
			{
				System.out.println(set.getInt("id") +" "+ set.getString("name") +" "+ set.getInt("marks"));
			}
			
//			update
			String updateSQL = "update student set marks = 90 where id = 2";
			stmt.executeUpdate(updateSQL);
			
			System.out.println("Record Updated");
			
//			delete
			String deleteSQL = "delete from student where id = 1";
			stmt.executeUpdate(deleteSQL);
			
			System.out.println("Record Deleted");
			
//			close connection
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
