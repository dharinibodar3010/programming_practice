package com.dec25122025;

import java.util.Scanner;

public class ElseIf {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your marks: ");
		int marks = sc.nextInt();
		
		if(marks >= 70)
		{
			System.out.println("A Grade");
		}
		else if(marks >= 60)
		{
			System.out.println("B Grade");
		}
		else if(marks >= 50)
		{
			System.out.println("C Grade");
		}
		else if(marks >= 40)
		{
			System.out.println("D Grade");
		}
		else
		{
			System.out.println("Fail");
		}

		sc.close();
		
	}

}
