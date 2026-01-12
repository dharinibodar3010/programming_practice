package com.jan08012026;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializeEx 
{
	public static void main(String[] args) 
	{
		try
		{
			Student s1 = new Student(101, "Dharini");
			FileOutputStream fout = new FileOutputStream("D://java.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(s1);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Executed");
	}
}

// ObjectOutputStream - Serialize
// ObjectOutputStream - Deserialize
