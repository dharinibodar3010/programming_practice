package com.example;

import java.util.Scanner;

/*Lab 8.3: Implement string comparison using equals() and compareTo() methods.*/

public class StringComparison 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter First String: ");
		String s1 = sc.nextLine();
		
		System.out.println("Enter Second String: ");
		String s2 = sc.nextLine();
		
		System.out.println("Using equals(): " + s1.equals(s2));
		System.out.println("Using comapreTo(): " + s1.compareTo(s2));
		
		sc.close();
	}

}

/* 1. equals(): returns boolean value -> true or false.
   
   2. comapreTo(): returns integer value -> 0(zero), positive(first > second), 
	  negative(first < second) */