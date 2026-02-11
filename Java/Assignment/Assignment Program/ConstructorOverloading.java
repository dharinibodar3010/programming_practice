package com.example;

/*Lab 7.2: Demonstrate constructor overloading by passing different types of parameters.*/

class Employee
{
	int id;
	String name;
	double salary;
	
	Employee(String name)
	{
		this.id = 0;
		this.name = name;
		this.salary = 0;
	}
	
	Employee(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	Employee(int id, String name, double salary)
	{
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	void display()
	{
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("Salary: " + salary);
		System.out.println("---------------------");
	}
}


public class ConstructorOverloading 
{
	public static void main(String[] args) 
	{
		Employee e1 = new Employee("Dharini");
		Employee e2 = new Employee(101, "Radha");
		Employee e3 = new Employee(102, "Dhara", 50000.50);
		
		e1.display();
		e2.display();
		e3.display();			
	} 
		
}
