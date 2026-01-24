package com.jan23012026;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm implements ActionListener 
{
	JFrame frame;
	JLabel l1, l2;
	JTextField tf;
	JPasswordField pf;
	JButton btn;
	JCheckBox cb;
	
	public LoginForm() 
	{
		frame = new JFrame("Login Form");
		
		tf = new JTextField();	//email
		tf.setBounds(150, 50, 150, 25);
		
		pf = new JPasswordField();	//password
		pf.setBounds(150, 100, 150, 25);
		
		l1 = new JLabel("Enter Email: ");
		l1.setBounds(50, 50, 120, 25);
		
		l2 = new JLabel("Enter Password: ");
		l2.setBounds(50, 100, 120, 25);
		
		btn = new JButton("Login");
		btn.setBounds(130, 160, 100, 25);
		
		btn.addActionListener(this);
		
		cb = new JCheckBox("Show Password");
		cb.setBounds(170, 130, 150, 20);
		
		cb.addActionListener(this);
		
		frame.add(tf);
		frame.add(l1);
		frame.add(l2);
		frame.add(pf);
		frame.add(btn);
		frame.add(cb);
		frame.setSize(400, 300);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) 
	{
		new LoginForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String email = tf.getText();
		String pass = new String(pf.getPassword());
		
		//-----checkbox event-----
		if(e.getSource() == cb)		
		{
			if(cb.isSelected())
			{
				pf.setEchoChar((char) 0);	//visible password
			}
			else
			{
				pf.setEchoChar('*');	//invisible password
			}
		}
		
		//-----button event-----
		if(e.getSource() == btn)	
		{
			if(email.equals("dharini@gmail.com") && pass.equals("1234"))
			{
				new CafeEx();
				//JOptionPane.showMessageDialog(frame, "Login Successful");
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Invalid Email or Password");
			}
		}
	}
}

