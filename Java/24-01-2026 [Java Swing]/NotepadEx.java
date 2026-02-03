package com.jan24012026;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class NotepadEx implements ActionListener 
{
	JFrame frame;
	JMenuBar mb;
	JMenu file, edit, help;
	JMenuItem cut, copy, paste, selectAll;
	JTextArea ta;
	
	public NotepadEx() 
	{
		frame = new JFrame();
		
		cut = new JMenuItem("cut");
		copy = new JMenuItem("copy");
		paste = new JMenuItem("paste");
		selectAll = new JMenuItem("selectAll");
		
		mb = new JMenuBar();
		
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectAll);
		
		mb.add(file);
		mb.add(edit);
		mb.add(help);
		
		ta = new JTextArea();
		ta.setBounds(5, 5, 360, 320);
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		
		frame.add(ta);
		frame.add(mb);
		frame.setJMenuBar(mb);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new NotepadEx();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == cut) 
		{
			ta.cut();
		}
		if (e.getSource() == copy) 
		{
			ta.copy();
		}
		if (e.getSource() == paste) 
		{
			ta.paste();
		}
		if (e.getSource() == selectAll) 
		{
			ta.selectAll();
		}
		
	}

}
