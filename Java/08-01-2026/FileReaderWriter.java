package com.jan08012026;

import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderWriter 
{
	public static void main(String[] args) throws Exception
	{
		FileWriter fw = new FileWriter("tops1.txt");
		String s = "Hello, this is my data";
		fw.write(s);
		fw.flush();
		fw.close();
		
		FileReader fr = new FileReader("tops1.txt");
		
		int i = 0;
		
		while((i = fr.read()) != -1)
		{
			System.out.println((char)i);
		}
		
	}

}
