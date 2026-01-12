package com.jan08012026;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Student implements Serializable
{
	int sid;
	String sname;
	
	public Student(int sid, String sname) 
	{
		this.sid = sid;
		this.sname = sname;
	}
}

public class SerializationDemo 
{
	public static void main(String[] args) throws Exception
	{
		Student s = new Student(1, "Dharini");
		FileOutputStream fos = new FileOutputStream("sry");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
	}
}
