package com.example;

/*Lab 12.3: Create a custom exception class and use it in your program.*/

// Custom Exception Class
class InvalidAgeException extends Exception
{
	public InvalidAgeException(String Message) 
	{
		super(Message);
	}
}

public class CustomExceptionDemo 
{
	// Method that throws custom exception
	static void checkAge(int age) throws InvalidAgeException
	{
		if(age < 18)
		{
			throw new InvalidAgeException("Age must be 18 or above");
		}
		else
		{
			System.out.println("You are eligible to vote");
		}
	}
	
	public static void main(String[] args) 
	{
		try
		{
			checkAge(16); // invalid age
		}
		catch(InvalidAgeException e)
		{
			System.out.println("Custom exception caught: " +e.getMessage());
		}
		
		System.out.println("Program continues normally");	
	}
}
