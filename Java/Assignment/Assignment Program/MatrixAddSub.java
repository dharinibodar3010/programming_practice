package com.example;

/*Lab 8.1: Write a program to matrix addition and subtraction using 2D arrays.*/

import java.util.Scanner;

public class MatrixAddSub 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
//		Taking number of rows and columns
		System.out.println("Enter number of rows: ");
		int rows = sc.nextInt();
		
		System.out.println("Enter number of columns: ");
		int cols = sc.nextInt();
		
//		Declaring matrices
		int matrix1[][] = new int[rows][cols];
		int matrix2[][] = new int[rows][cols];
		int sum[][] = new int[rows][cols];
		int sub[][] = new int[rows][cols];
		
//		Input for first elements
		System.out.println("Enter elements of first matrix: ");
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				matrix1[i][j] = sc.nextInt();
			}
		}
		
//		Input for second elements
		System.out.println("Enter elements of second matrix: ");
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				matrix2[i][j] = sc.nextInt();
			}
		}
		
//		Matrix Addition and Subtraction 
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				sum[i][j] = matrix1[i][j] + matrix2[i][j];
				sub[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		
//		Display Addition
		System.out.println("Matrix Addition: ");
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				System.out.print(sum[i][j] + " ");
			}
			
			System.out.println();
		}
		
//		Display Subtraction
		System.out.println("Matrix Subtraction: ");
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				System.out.print(sub[i][j] + " ");
			}
			
			System.out.println();
		}
		
		sc.close();
	}

}
