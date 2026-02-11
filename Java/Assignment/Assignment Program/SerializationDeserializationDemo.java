package com.example;

/*Lab 14.3: Create a program that demonstrates object serialization and deserialization.*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Step 1: Create a Serializable class

class Student1 implements Serializable
{
	int id;
	String name;
	
	Student1(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
}

// Step 2: Serialization (Write Object to File)

public class SerializationDeserializationDemo 
{
	public static void main(String[] args) 
	{
		try
		{
			Student1 s = new Student1(1, "Dharini");
			
			FileOutputStream fos = new FileOutputStream("student.txt");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(s);	// serialization
			oos.close();
			fos.close();
			
			System.out.println("Object Serialized Successfully");
			
// Step 3: Deserialization (Read Object from File)
			
			FileInputStream fis = new FileInputStream("student.txt");
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Student1 s1 = (Student1)ois.readObject();
			
			System.out.println("ID: " + s1.id);
			System.out.println("Name: " + s1.name);
			
			ois.close();
			fis.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
}



