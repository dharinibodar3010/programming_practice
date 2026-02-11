package com.example;

/*Lab 3.3: Write a program to display the Fibonacci series using a loop.*/

import java.util.Scanner;

public class FibonacciSeriess {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of terms: ");
		int n = sc.nextInt();
		
		int a = 0, b = 1;
		
		System.out.println("Fibonacci Series:");
		
		for(int i = 1; i <= n; i++) {
			System.out.print(a + " ");
			
			int next = a + b;
			a = b;
			b = next;
		}
		
		sc.close();
		
	}

}
