package com.example;

/*Lab 5.1: Write a program to find the maximum of three numbers using a method.*/

public class MaximumNumber 
{
	static int findMax(int a, int b, int c)
	{
		int max = a;
		
		if(b > max)
		{
			max = b;
		}
		
		if(c > max)
		{
			max = c;
		}
		
		return max;
	}
	
	public static void main(String[] args) 
	{
		int x = 10;
		int y = 25;
		int z = 15;
		
		int result = findMax(x, y, z);
		
		System.out.println("Maximum number is: " + result);
	}

}
