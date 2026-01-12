package com.jan08012026;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileEx1 
{
	public static void main(String[] args) throws IOException
	{
		String s = "Welcome to tops";
		FileOutputStream fout = new FileOutputStream("D://hello.txt");
		fout.write(s.getBytes());
		System.out.println("Executed");
		
//		finally
//		{
//			int data = 10;
//			System.out.println(data);
//		}
	}
} 

//FileOutputStream - write
//FileInputStream - read