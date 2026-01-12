package com.jan03012026;

import java.util.Scanner;

public class ArrayEx5 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
//		array size
		System.out.println("Enter array size: ");
		int size = sc.nextInt();
		
//		array elements
		System.out.println("Enter Elements: ");
		int marks[] = new int[size];
		
		int total = 0;
		
		for(int i = 0; i < size; i++)
		{
			marks[i] = sc.nextInt();
		}
		
		for(int i = 0; i < size; i++)
		{
			total += marks[i];
		}
		
		System.out.println(total);
	}

}
