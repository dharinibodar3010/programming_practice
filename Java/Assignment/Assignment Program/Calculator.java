package com.example;

/*Lab 2.2: Create a calculator using arithmetic and relational operators.*/

import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Taking user input
		System.out.println("Enter first number: ");
		int a = sc.nextInt();
		
		System.out.println("Enter second number: ");
		int b = sc.nextInt();
		
		// Arithmetic Operators 
		System.out.println("\n-----Arithmetic Operators-----");
		System.out.println("Addition: " + (a + b));
		System.out.println("Substraction: " + (a - b));
		System.out.println("Multiplicatio: " + (a * b));
		System.out.println("Division: " + (a / b));
		System.out.println("Modulus: " + (a % b));
		
		// Relational Operators
		System.out.println("\n-----Relational Operators-----");
		System.out.println("a == b : " + (a == b));
		System.out.println("a != b : " + (a != b));
		System.out.println("a >= b : " + (a >= b));
		System.out.println("a > b : " + (a > b));
		System.out.println("a <= b : " + (a <= b));
		System.out.println("a < b : " + (a < b));
		
		sc.close(); 
	}

}
