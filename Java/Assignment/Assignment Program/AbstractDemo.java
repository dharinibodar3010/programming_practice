package com.example;

/*Lab 10.1: Create an abstract class and implement its methods in a subclass.*/

// Abstract class
abstract class ShapeInfo
{
	// Abstract method (no body)
	abstract void draw();
	
	// Concrete method
	void Info()
	{
		System.out.println("This is a shape.");
	}
}

// Subclass
class CircleInfo extends ShapeInfo
{
	// Implementing abstract method 
	@Override
	void draw() 
	{
		System.out.println("Drawing a Circle");
	}
}

// Main class
public class AbstractDemo 
{
	public static void main(String[] args) 
	{
		// Shape reference, Circle object (runtime polymorphism)
		
		ShapeInfo s = new CircleInfo();
		
		s.draw();
		s.Info();
		
	}

}
