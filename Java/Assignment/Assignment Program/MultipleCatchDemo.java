package com.example;

/*Lab 12.2: Implement multiple catch blocks for different types of exceptions.*/

public class MultipleCatchDemo 
{
	public static void main(String[] args) 
	{
		try
		{
//			ArithmeticException
			int a = 10 / 0;
			
//			ArrayIndexOutOfBoundsException
			int arr[] = {1, 2, 3};
			System.out.println(arr[5]);
			
//			NullPointerException
			String str = null;
			System.out.println(str.length());
		}
		catch(ArithmeticException e)
		{
			System.out.println("Arithmetic Exception occurred");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array Index Out Of Bounds Exception occurred");
		}
		catch(NullPointerException e)
		{
			System.out.println("Null Pointer Exception occurred");
		}
		catch(Exception e)
		{
			System.out.println("General Exception occurred");
		}
		System.out.println("Program executed successfully");
	}
}
