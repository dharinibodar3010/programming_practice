package com.jan20012026;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegistrationForm implements ActionListener
{
	JFrame frame;
	JTextField tf1, tf2, tf3, tf4, tf5;
	JLabel l1, l2, l3, l4, l5, title;
	JButton btn1, btn2, btn3;
	
	public RegistrationForm() 
	{
//		JFrame -> main window of the GUI
		frame = new JFrame();
		
//		Title -> display the heading of the form
		Font titleFont = new Font("Arial", Font.BOLD, 20);
		title = new JLabel("Registration Form");
		title.setBounds(320, 50, 200, 30);
		title.setFont(titleFont);
		
//		JTextfield -> takes input from the user
		tf1 = new JTextField();
		tf1.setBounds(350, 120, 200, 25);
		
		tf2 = new JTextField();
		tf2.setBounds(350, 170, 200, 25);
		
		tf3 = new JTextField();
		tf3.setBounds(350, 220, 200, 25);
		
		tf4 = new JTextField();
		tf4.setBounds(350, 270, 200, 25);
		
		tf5 = new JTextField();
		tf5.setBounds(350, 320, 200, 25);
		
//		JLabel -> displays text on the screen
		Font labelFont = new Font("Arial", Font.BOLD, 14);
		
		l1 = new JLabel("Registration no: ");
		l1.setBounds(225, 120, 200, 25);
		l1.setFont(labelFont);
		
		l2 = new JLabel("Student name: ");
		l2.setBounds(225, 170, 200, 25);
		l2.setFont(labelFont);
		
		l3 = new JLabel("Batch: ");
		l3.setBounds(225, 220, 200, 25);
		l3.setFont(labelFont);
		
		l4 = new JLabel("Department: ");
		l4.setBounds(225, 270, 200, 25);
		l4.setFont(labelFont);
		
		l5 = new JLabel("Address: ");
		l5.setBounds(225, 320, 200, 25);
		l5.setFont(labelFont);
		
//		JButton -> performs action when clicked
		Font buttonFont = new Font("Arial", Font.BOLD, 16);
		
		btn1 = new JButton("Reset");
		btn1.setBounds(220, 370, 150, 50);
		btn1.setFont(buttonFont);
		
		btn2 = new JButton("Register");
		btn2.setBounds(400, 370, 150, 50);
		btn2.setFont(buttonFont);
		
		btn3 = new JButton("Back");
		btn3.setBounds(20, 445, 150, 50);
		btn3.setFont(buttonFont);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		frame.add(tf1);
		frame.add(tf2);
		frame.add(tf3);
		frame.add(tf4);
		frame.add(tf5);
		frame.add(title);
		frame.add(l1);
		frame.add(l2);
		frame.add(l3);
		frame.add(l4);
		frame.add(l5);
		frame.add(btn1);
		frame.add(btn2);
		frame.add(btn3);
		frame.setTitle("Create Profile");
		frame.setLayout(null);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) 
	{
		new RegistrationForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
//		Reset
		if (e.getSource() == btn1) 
		{
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
		}
		
//		Register
		else if (e.getSource() == btn2)
		{
			String reg = tf1.getText();
			String name = tf2.getText();
			String batch = tf3.getText();
			String dept = tf4.getText();
			String address = tf5.getText();
			
			if (reg.isEmpty() || name.isEmpty() || batch.isEmpty() || dept.isEmpty() || address.isEmpty()) 
			{
				JOptionPane.showMessageDialog(frame, "All fields are required!");
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Registration Successful!");
			}
		}
		
//		Back
		else if (e.getSource() == btn3)
		{
			frame.dispose();
		}
		
	}
}
