package com.example;

/*Lab 9.1: Write a program that inheritance using extends keyword.*/

// Parent class
class Person
{
	String name;
	int age;
	
	void displayperson()
	{
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
	}
}

// Child class using extends keyword
class StudentInfo extends Person
{
	int rollNo;
	
	void displaystudentInfo()
	{
		System.out.println("Roll No: " + rollNo);
	}
}

// Main class
public class ExtendsKeyword 
{
	public static void main(String[] args) 
	{
		StudentInfo s = new StudentInfo();
		
		// parent class variables
		s.name = "Dharini";
		s.age = 25;
		
		// child class variable
		s.rollNo = 101;
		
		s.displayperson();	// parent method
		s.displaystudentInfo();	// child method
	}

}
