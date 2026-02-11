package com.example;

/*Lab 12.1: Write a program to demonstrate exception handling using try-catch-finally.*/

public class TryCatchFinallyDemo 
{
	public static void main(String[] args) 
	{
		try
		{
			int a = 10;
			int b = 0;
			
			int result = a / b;	// Exception occurs here
			
			System.out.println("Result = " + result);
		}
		catch(ArithmeticException e)
		{
			System.out.println("Cannot divide by zero");
		}
		finally
		{
			System.out.println("Finally block always executes");
		}
		System.out.println("Program continues normally");
	}

}
