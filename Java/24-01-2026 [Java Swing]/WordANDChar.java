package com.jan24012026;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class WordANDChar 
{
	static JFrame frame;
	JTextArea ta;
	JButton btn;
	
	public WordANDChar() 
	{
		frame = new JFrame("");
		
		ta = new JTextArea();
		ta.setBounds(79, 26, 246, 111);
		
		btn = new JButton("Count");
		btn.setBounds(161, 160, 89, 23);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String data = ta.getText().toString();
				String data1[] = data.split("\\s");
				System.out.println("Characters are: " + data.length());
				System.out.println("Words are: " + data1.length);
			}
		});
		
		frame.add(ta);
		frame.add(btn);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		String name = JOptionPane.showInputDialog(frame, "Enter Your Name");
		
//		String a = name.toString();
//		if (a.length() < 10 && a.length() >= 30) 
//		{
//			
//		}
		
		if (name.equals("tops")) 
		{
			new WordANDChar();
		}
	}

}
