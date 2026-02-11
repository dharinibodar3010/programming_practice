package com.example;

/*Lab 6.3: Implement method overriding to show polymorphism in action.*/

// Parent class 
class AnimaL1
{
	void sound()
	{
		System.out.println("Animal is makes a sound");
	}
}

// Child class
class DoG1 extends AnimaL1
{
	@Override	// Method overriding
	void sound() 
	{
		System.out.println("Dog barks");	
	}	
}

// Main class
public class Polymorphism 
{
	public static void main(String[] args) 
	{
		// Parent reference, child object
		AnimaL1 a = new DoG1();
		
		a.sound();	// Runtime polymorphism	
	}
}
