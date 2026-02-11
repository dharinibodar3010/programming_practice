package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*Lab 14.2: Implement a program that reads a file line by line using BufferedReader.*/

public class BufferedReaderDemo 
{
	public static void main(String[] args) 
	{
		try
		{
			FileReader fr = new FileReader("sample.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			
//			Reading file line by line
			
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			
			br.close();
			fr.close();
		}
		
		catch(IOException e)
		{
			System.out.println("Error while reading file");
		}
	}
}
