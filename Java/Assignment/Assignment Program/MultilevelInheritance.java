package com.example;

/*Lab 6.2: Create a class hierarchy and demonstrate multilevel inheritance.*/

// Grandparent class
class Vehicle
{
	void start()
	{
		System.out.println("Vehicle is starting");
	}
}

// Parent class
class Car extends Vehicle
{
	void drive()
	{
		System.out.println("Car is driving");
	}
}

// Child class
class SportsCar extends Car
{
	void speed()
	{
		System.out.println("Sport car is very fast");
	}
}

// Main class
public class MultilevelInheritance 
{
	public static void main(String[] args) 
	{
		SportsCar sc = new SportsCar();
		
		sc.start();
		sc.drive();
		sc.speed();
	}

}
