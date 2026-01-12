package com.jan08012026;

public class ThrowEx2 
{
	static void validate(int age) throws MyException
	{
		if(age>18)
		{
			System.out.println("Eligible to vote");
		}
		else
		{
			throw new MyException();
		}
	}
	
	public static void main(String[] args) throws MyException
	{
		validate(17);
	}

}
