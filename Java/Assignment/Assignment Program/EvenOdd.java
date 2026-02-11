package com.example;

/*Lab 3.1: Write program to find if a number is even or odd using an if-else statement.*/

import java.util.Scanner;

public class EvenOdd {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Taking input from user
		System.out.println("Enter a number: ");
		int num = sc.nextInt();
		
		// Checking even or odd using if-else
		if(num % 2 == 0) {
			System.out.println(num + " is Even");
		}
		else {
			System.out.println(num + " is Odd");
		}
		
		sc.close();	
	}
}
