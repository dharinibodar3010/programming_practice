package com.jan27012026;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelEx 
{
	JFrame frame;
	JPanel jp1, jp2;
	JButton btn1, btn2;
	
	public JPanelEx() 
	{
		frame = new JFrame();
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		btn1 = new JButton("ABCD");
		btn2 = new JButton("PQRS");
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				jp2.setVisible(true);
				jp1.setVisible(false);
				
			}
		});
		
		jp1.add(btn1);
		jp2.add(btn2);
		jp2.setVisible(false);
		frame.add(jp1);
		frame.add(jp2);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
	}
	public static void main(String[] args) 
	{
		new JPanelEx();
	}
}
