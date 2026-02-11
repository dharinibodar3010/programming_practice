package com.example;

/*Lab 9.2: Implement runtime polymorphism by overriding methods in the child class.*/

// Parent class
class Shape
{
	void draw()
	{
		System.out.println("Drawing a shape");
	}
}

// Child class 1
class Circle extends Shape
{
	@Override 
	void draw()
	{
		System.out.println("Drawing a circle");
	}
}

// Child class 2
class Rectangle extends Circle
{
	@Override 
	void draw()
	{
		System.out.println("Drawing a rectangle");
	}
}

// Main class
public class RuntimePolymorphism 
{
	public static void main(String[] args) 
	{
//		Shape s;	// parent reference
//		
//		s = new Circle();
//		s.draw();
//		
//		s = new Rectangle();
//		s.draw();
		
//		OR
		
		Shape s1 = new Circle();
		Shape s2 = new Rectangle();
		
		s1.draw();
		s2.draw();
		
	}

}
