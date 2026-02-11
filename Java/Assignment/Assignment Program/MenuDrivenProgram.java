package com.example;

/*Lab 3.2: Implement a simple menu-driven program using a switch-case.*/

import java.util.Scanner;

public class MenuDrivenProgram {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-----Menu-----");
		System.out.println("1. Addition");
		System.out.println("2. Substraction");
		System.out.println("3. Multiplication");
		System.out.println("4. Division");
		System.out.println("5. Modulus");
		
		System.out.print("Enter your choice (1-4): ");
		int choice = sc.nextInt();
		
		System.out.print("Enter first number: ");
		int a = sc.nextInt();
		
//		System.out.print("Enter operator (+, -, *, /, %): ");
//		char op = sc.next().charAt(0);
		
		System.out.print("Enter second number: ");
		int b = sc.nextInt();
		
		switch(choice) {
		case 1: 
			System.out.println("Result = " + (a + b));
			break;
		
		case 2: 
			System.out.println("Result = " + (a - b));
			break;
		
		case 3: 
			System.out.println("Result = " + (a * b));
			break;
		
		case 4: 
			if(b != 0) {
			System.out.println("Result = " + (a / b));
			}
			else {
				System.out.println("Division by zero is not allowed");
			}
			break;
		
		case 5: 
			System.out.println("Result = " + (a % b));
			break;
		
		default: 
			System.out.println("Invalid choice");
		}
		
		sc.close();
	}

}
