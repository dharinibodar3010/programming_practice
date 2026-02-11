package com.example;

/*Lab 7.1: Write a program to create and initialize an object using 
		   a parameterized constructor.*/

class Students
{
	int id;
	String name;
	
	// Parameterized constructor
	Students(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	void display()
	{
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
	}
}

public class ParameterizedCon 
{
	public static void main(String[] args) 
	{
		Students s = new Students(101, "Dharini");
		
		s.display();
	}

}
