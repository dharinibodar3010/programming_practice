package com.JDBC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*Lab 10: 
o Create a simple Swing GUI with input fields for id, fname, lname, and email.
o Implement CRUD operations (Insert, Update, Select, Delete) using JDBC and MySQL.
o On button clicks, the program should interact with the database and perform the
  appropriate operation (insert, update, display records, or delete records).*/

public class StudentCRUDSwing implements ActionListener 
{
	JFrame frame;
	JTextField idtf, fnametf, lnametf, emailtf;
	JButton insertbtn, updatebtn, selectbtn, deletebtn;
	
	String host = "jdbc:mysql://localhost:3306/";
	String db = "jdbc";
	String url = host + db;
	
	public StudentCRUDSwing() 
	{
		frame = new JFrame();
		
		idtf = new JTextField(20);
		fnametf = new JTextField(20);		
		lnametf = new JTextField(20);		
		emailtf = new JTextField(20);
		
		insertbtn = new JButton("Insert");
		updatebtn = new JButton("Update");
		deletebtn = new JButton("Delete");
		selectbtn = new JButton("Display");
		
		insertbtn.addActionListener(this);
		updatebtn.addActionListener(this);
		selectbtn.addActionListener(this);
		deletebtn.addActionListener(this);
		
		frame.add(new JLabel("ID:"));
		frame.add(idtf);
		frame.add(new JLabel("FName:"));
		frame.add(fnametf);
		frame.add(new JLabel("LName:"));
		frame.add(lnametf);
		frame.add(new JLabel("Email:"));
		frame.add(emailtf);
		frame.add(insertbtn);
		frame.add(updatebtn);
		frame.add(deletebtn);
		frame.add(selectbtn);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		new StudentCRUDSwing();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "");
			
//			insert
			if (e.getSource() == insertbtn)
			{
				String sql = "insert into student2 (fname, lname, email) values (?, ?, ?)";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, fnametf.getText());
				ps.setString(2, lnametf.getText());
				ps.setString(3, emailtf.getText());
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(frame, "Record Inserted");
			}
			
//			update
			if (e.getSource() == updatebtn)
			{
				String sql = "update student2 set fname = ?, lname = ?, email = ? where id = ?";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, fnametf.getText());
				ps.setString(2, lnametf.getText());
				ps.setString(3, emailtf.getText());
				ps.setInt(4, Integer.parseInt(idtf.getText()));
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(frame, "Record Updated");
			}
			
//			delete
			if (e.getSource() == deletebtn) 
			{
				String sql = "delete from student2 where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setInt(1, Integer.parseInt(idtf.getText()));
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(frame, "Record Deleted");
			}
			
//			select/display
			if (e.getSource() == deletebtn) 
			{ 
				String sql = "select * from student";
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				ResultSet set = ps.executeQuery();
				
				while (set.next()) 
				{
					int id = set.getInt("id");
					String fname = set.getString("fname");
					String lname = set.getString("lname");
					String email = set.getString("email");	
				}
				con.close();
			}
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}
		
	}

}