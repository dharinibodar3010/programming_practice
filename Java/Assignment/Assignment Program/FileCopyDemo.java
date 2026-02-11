package com.example;

/*Lab 16.2: Implement a file copy program using FileInputStream and FileOutputStream.*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyDemo 
{
	public static void main(String[] args) 
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try
		{
//			source file
			fis = new FileInputStream("sample.txt");
			
//			destination file
			fos = new FileOutputStream("destination.txt");
			
			int data;
			
//			read and write byte by byte
			while ((data = fis.read()) != -1) 
			{
				fos.write(data);
			}
			
			System.out.println("File copied successfully");
		}
		catch (IOException e) 
		{
			System.out.println("File copy error");
		}
		finally
		{
			try
			{
				if (fis != null) 
				{
					fis.close();
				}
				if (fos != null)
				{
					fos.close();
				}
			}
			catch (IOException e) 
			{
				System.out.println("Error while closing file");
			}
		}
	}
}
