package com.example;

import java.util.Scanner;

/*Lab 8.2: Create a program to reverse a string and check for palindromes.*/

public class ReversePalindrome 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
//		Input string
		System.out.println("Enter a String: ");
		String str = sc.next();
		
		String reverse = "";
		
//		Reverse the string
		for(int i = str.length() - 1; i >= 0; i--)
		{
			reverse = reverse + str.charAt(i);
		}
		
//		Display reversed string
		System.out.println("Reversed string: " + reverse);
		
//		Check palindrome
		if(str.equals(reverse))
		{
			System.out.println("The string is a Palindrome");
		}
		else
		{
			System.out.println("The string is NOT a Palindrome");
		}
		sc.close();
	}

}
