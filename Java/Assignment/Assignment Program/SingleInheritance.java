package com.example;

/*Lab 6.1: Write a program demonstrating single inheritance.*/

// Parent class
class Animal
{
	void eat()
	{
		System.out.println("Animal is eating");
	}
}

// Child class
class Dog extends Animal
{
	void bark()
	{
		System.out.println("Dog is barking");
	}
}

// Main class
public class SingleInheritance 
{
	public static void main(String[] args) 
	{
		Dog d = new Dog();
		
		d.eat();	// Parent class method
		d.bark();	// Child class method
		
	}

}
