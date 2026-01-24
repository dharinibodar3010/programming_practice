package com.jan23012026;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CafeEx extends WindowAdapter implements ActionListener 
{
	JFrame frame;
	JLabel l1, tablelabel;
	JCheckBox cb1, cb2, cb3;
	JButton btn;
	int tableNo;
	
	public CafeEx() 
	{
		String input = JOptionPane.showInputDialog("Enter Table Number: ");
		
		if (input == null || input.isEmpty()) 
		{
			JOptionPane.showInternalMessageDialog(null, "Table number required!");
			return;
		}
		
		tableNo = Integer.parseInt(input);
		 
		frame = new JFrame("Cafe Ordering System");
		
		tablelabel = new JLabel("Table No: " + tableNo);
		tablelabel.setBounds(20, 10, 125, 25);
		
		l1 = new JLabel("Menu");
		l1.setBounds(192, 10, 46, 14);
		
		cb1 = new JCheckBox("Pizza - Rs.100");
		cb1.setBounds(167, 40, 130, 23);
		
		cb2 = new JCheckBox("Burger - Rs.70");
		cb2.setBounds(167, 80, 130, 23);
		
		cb3 = new JCheckBox("Coffee - Rs.120");
		cb3.setBounds(167, 120, 130, 23);
		
		btn = new JButton("Order");
		btn.setBounds(167, 160, 130, 23);
		
		btn.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		
		frame.add(tablelabel);
		frame.add(l1);
		frame.add(cb1);
		frame.add(cb2);
		frame.add(cb3);
		frame.add(btn);
		frame.setSize(500, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
//	public static void main(String[] args) 
//	{
//		new CafeEx();
//	}

	void saveToFile(String data)
	{
		try 
		{
			FileWriter fw = new FileWriter("CafeEx.txt", true);
			fw.write("Table No: " + tableNo + "\n");
			fw.write(data);
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) 
	{
		int a = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?");
		
		if (a == 0) 
		{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int total = 0;
		StringBuilder sb = new StringBuilder();
		
		if (e.getSource() == btn) 
		{
			if (cb1.isSelected()) 
			{
				sb.append("\n Pizza Rs.100");
				total+=100;
			}
			if (cb2.isSelected()) 
			{
				sb.append("\n Burger Rs.70");
				total+=70;
			}
			if (cb3.isSelected()) 
			{
				sb.append("\n Coffee Rs.120");
				total+=120;
			}
			sb.append("\nTotal Bill is : ").append(total);
			sb.append("\n------------------------------------\n");
			//JOptionPane.showMessageDialog(frame, "\n Total Bill is: " + sb.toString());
			JOptionPane.showMessageDialog(frame, "Table No : " + tableNo + "\n\n" + sb.toString());
			saveToFile(sb.toString());
		}
		
	}

}
