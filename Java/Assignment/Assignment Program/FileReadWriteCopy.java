package com.example;

/*Lab 16.3: Create a program that reads from one file and writes the content to another file.*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWriteCopy 
{
	public static void main(String[] args) 
	{
		FileReader fr = null;
		FileWriter fw = null;
		
		try
		{
//			source file
			fr = new FileReader("sample.txt");
			
//			destionation file
			fw = new FileWriter("output.txt");
			
			int ch;
			
//			read and write character by character
			while ((ch = fr.read()) != -1) 
			{
				fw.write(ch);
			}
			
			System.out.println("File copied successfully");
		}
		catch (IOException e) 
		{
			System.out.println("File read/write error");
		}
		finally
		{
			try
			{
				if (fr != null) 
				{
					fr.close();
				}
				if (fw != null) 
				{
					fw.close();
				}
			}
			catch (IOException e) 
			{
				System.out.println("Error while closing file");
			}
		}
	}
}
