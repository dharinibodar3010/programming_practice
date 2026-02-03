package com.jan29012026;

public class ThrowEx 
{
	static void validate(int age) 
	{
		if (age < 18) 
		{
			throw new ArithmeticException("Age is not valid");
		}
		else
		{
			System.out.println("Eligible to vote");
		}
	}
	
	public static void main(String[] args) 
	{
		validate(15);	//19
	}

}
