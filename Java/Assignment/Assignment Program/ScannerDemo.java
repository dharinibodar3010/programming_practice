package com.example;

/*Lab 16.1: Write a program to read input from the console using Scanner.*/

import java.util.Scanner;

public class ScannerDemo 
{
	public static void main(String[] args) 
	{
//		create scanner object
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your age: ");
		int age = sc.nextInt();
		
		System.out.println("Enter your name: ");
		String name = sc.next();
		
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		
//		close scanner
		sc.close();
	}
}
