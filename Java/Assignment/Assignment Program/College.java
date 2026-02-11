package com.example;

/*Lab 5.3: Create a class with static variables and methods to demonstrate their use.*/

public class College 
{
	//static variable
	static String collegeName = "ABC College";
	
	//instance variable
	int studentId;
	String studentName;
	
	//constructor
	College(int id, String name)
	{
		studentId = id;
		studentName = name;
	}
	
	//static method
	static void showCollegeName()
	{
		System.out.println("College Name: " + collegeName);
	}
	
	//instance method
	void showStudentDetails()
	{
		System.out.println("ID: " + studentId);
		System.out.println("Name: " + studentName);
	}
	
	public static void main(String[] args) 
	{
		// calling static method
		College.showCollegeName();
		
		// creating objects
		College s1 = new College(101, "Dharini");
		College s2 = new College(102, "riddhi");
		
		// calling instance method
		s1.showStudentDetails();
		s2.showStudentDetails();
		
	}

}
