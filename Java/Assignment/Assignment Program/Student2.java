package com.example;

/*Lab 4.2: Create multiple constructors in a class and demonstrate constructor overloading.*/

public class Student2 {
	
	int id;
	String name;
	int age;

	// Default Constructor
	Student2() 
	{
		id = 0;
		name = "Unkown";
		age = 0;
	}
	
	// Constructor with 2 parameters
	Student2(int i, String n)
	{
		id = i;
		name = n;
	}
	
	// Constructor with 3 parameters
	Student2(int i, String n, int a)
	{
		id = i;
		name = n;
		age = a;
	}

	void display()
	{
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("------------");
	}
	
	public static void main(String[] args) {
		
		Student2 s1 = new Student2();
		Student2 s2 = new Student2(101, "Ami");
		Student2 s3 = new Student2(102, "Bina", 30);
		
		s1.display();
		s2.display();
		s3.display();
	}
}
