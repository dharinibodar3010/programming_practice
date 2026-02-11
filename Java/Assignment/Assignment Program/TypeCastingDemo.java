package com.example;

/*Lab 2.3: Demonstrate type casting (implicit and explicit).*/

public class TypeCastingDemo {
	public static void main(String[] args) {
		
		// Implicit type casting (Widening) 
		int i = 10;
		double d = i;   // int -> double (automatic)
		
		System.out.println("Implicit type casting: ");
		System.out.println("int value = " + i);
		System.out.println("double value = " + d);
		
		// Explicit type casting (Narrowing)
		double x = 12.75;
		int y = (int) x;  // double -> int (manual)
		
		System.out.println("\nExplicit type casting: ");
		System.out.println("double value = " + x);
		System.out.println("int value = " + y);
	}

}
