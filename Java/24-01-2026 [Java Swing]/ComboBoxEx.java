package com.jan24012026;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ComboBoxEx 
{
	JFrame frame;
	JComboBox cb;
	String city[] = {"Rajkot", "Baroda", "Surat"};
	
	public ComboBoxEx() 
	{
		frame = new JFrame();
		
		cb = new JComboBox(city);
		
		frame.add(cb);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
	}
	public static void main(String[] args) 
	{
		new ComboBoxEx();
	}
}
