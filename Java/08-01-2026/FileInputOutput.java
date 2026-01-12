package com.jan08012026;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FileInputOutput 
{
	public static void main(String[] args) throws Exception
	{
		FileOutputStream fos = new FileOutputStream("tops.txt");
		
		System.out.println("Write Your Data Here = ");
		
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		fos.write(s.getBytes());
		fos.flush();
		fos.close();
		
		FileInputStream fis = new FileInputStream("tops.txt");
		
		int i = 0;
		
		while((i = fis.read()) != -1)
		{
			System.out.println((char) i);
		}
	}
}
