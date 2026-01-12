package com.jan03012026;

public class ArrayEx3 
{
	public static void main(String[] args) 
	{
		int data[][] = {{1,2,3}, {4,5,6}, {7,8,9}};
		
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[i].length; j++)
			{
				System.out.print(data[i][j] + " ");
			}
			
			System.out.println();
		}
		
	}

}
