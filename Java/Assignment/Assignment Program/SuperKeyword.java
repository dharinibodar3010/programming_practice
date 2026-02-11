package com.example;

/*Lab 9.3: Use the super keyword to call the parent class constructor and methods.*/

// Parent class
class PersonInfo
{
	PersonInfo()
	{
		System.out.println("Parent constructor");
	}
	
	void showDetails()
	{
		System.out.println("Parent method");
	}
}

// Child class
class EmployeeInfo extends PersonInfo
{
	EmployeeInfo()
	{
		super();	// call parent constructor
		
		System.out.println("Child constructor");
	}
	
	void showDetails()
	{	 
		System.out.println("Child method");
		
		super.showDetails();	// call parent method
	}
}

// Main class
public class SuperKeyword 
{
	public static void main(String[] args) 
	{
		EmployeeInfo e = new EmployeeInfo();
		e.showDetails();
	}

}
