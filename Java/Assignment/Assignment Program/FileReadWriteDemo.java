package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*Lab 14.1: Write a program to read and write content to a file using FileReader and
FileWriter.*/

public class FileReadWriteDemo 
{
	public static void main(String[] args) 
	{
		try
		{
			FileWriter fw = new FileWriter("sample.txt");
			fw.write("Hello Java File Handling\n");
			fw.write("This file is written using FileWriter");
			fw.close();
			
//			Reading from file
			FileReader fr = new FileReader("sample.txt");
			
			int ch;
			
			while((ch = fr.read()) != -1)
			{
				System.out.println((char) ch);
			}
			
			fr.close();
		}
		
		catch(IOException e)
		{
			System.out.println("File handling error occured");
		}	
	}
}
