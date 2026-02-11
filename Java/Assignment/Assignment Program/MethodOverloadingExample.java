package com.example;

/*Lab 5.2: Implement method overloading by creating methods for different data types.*/

public class MethodOverloadingExample 
{
	static int add(int a, int b)
	{
		return a+b;
	}
	
	static double add(double a, double b)
	{
		return a+b;
	}
	
	static String add(String a, String b)
	{
		return a+b;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(add(10, 20));
		System.out.println(add(10.5, 20.3));
		System.out.println(add("Hello", "Java"));
		
	}

}
