package com.feb05;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AllOperations 
{ 
	JFrame insertframe, deleteframe;
	JTextField tf1, tf2;
	JButton insert, delete;
	
	public void insertdata() 
	{
		insertframe = new  JFrame();
		
		tf1 = new JTextField(20);
		tf2 = new JTextField(20);
		insert = new JButton("Insert");
		
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String name = tf1.getText().toString();
				String pass = tf2.getText().toString();
				
				String host = "jdbc:mysql://localhost:3306/";
				String db = "test1";
				String url = host + db;
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, "root", "");
					
					String sql = "insert into students (name, password) values ('" + name + "', '" + pass + "')";
					Statement stmt = con.createStatement();
					int ans = stmt.executeUpdate(sql);
					
					if (ans > 0) 
					{
						System.out.println("Success");
						JOptionPane.showMessageDialog(insertframe, "success");
						insertframe.setVisible(true);	
					}
					else
					{
						System.out.println("Fail");
					}
				} 
				
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		
		insertframe.add(tf1);
		insertframe.add(tf2);
		insertframe.add(insert);
		insertframe.setSize(500, 500);
		insertframe.setLayout(new FlowLayout());
		insertframe.setVisible(true);
	}
	
	public void viewdata() 
	{
		String host = "jdbc:mysql://localhost:3306/";
		String db = "test1";
		String url = host+db;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "");
			
			String sql = "select * from students";
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(sql);
			System.out.println("ID" + "\t NAME" + "\t PASSWORD");
			
			while (set.next()) 
			{
				int id = set.getInt(1);
				String name = set.getString(2);
				String pass = set.getString(3);
				
				System.out.println(id + "\t " + name + "\t " + pass);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	 public void deletedata() 
	 {
		 deleteframe = new JFrame();
		 
		 tf1 = new JTextField(20);
		 
		 delete = new JButton("Delete");
		 
		 delete.addActionListener(new ActionListener() 
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = tf1.getText().toString();
				
				String host = "jdbc:mysql://localhost:3306/";
				String db = "test1";
				String url = host+db;
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, "root", "");
					
					String sql = "delete from students where id = '" + id + "'";
					Statement stmt = con.createStatement();
					int ans = stmt.executeUpdate(sql);
					
					if (ans > 0) 
					{
						System.out.println("Success");
						JOptionPane.showMessageDialog(deleteframe, "success");
						deleteframe.setVisible(true);
					}
					else
					{
						System.out.println("Fail");
					}
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		
		 deleteframe.add(tf1);
		 deleteframe.add(delete);
		 deleteframe.setSize(500, 500);
		 deleteframe.setLayout(new FlowLayout());
		 deleteframe.setVisible(true);
	}

}
