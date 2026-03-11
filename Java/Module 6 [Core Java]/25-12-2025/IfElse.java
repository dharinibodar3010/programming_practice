package com.dec25122025;

import java.util.Scanner;

public class IfElse {

	public static void main(String[] args) {
		
		// Scanner -> ClassName ObjectName = new ClassName();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your age: ");
		int age = sc.nextInt();
		
		if(age >= 18)
		{
			System.out.println("Eligible to vote");
		}
		else
		{
			System.out.println("NOT Eligible to vote");
		}
		
		sc.close();
	}

}
