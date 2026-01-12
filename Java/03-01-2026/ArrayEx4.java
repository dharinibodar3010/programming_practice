package com.jan03012026;

public class ArrayEx4 
{
	public static void main(String[] args) 
	{
		int A[] = {1,2,3,4,5};
		int B[] = new int[10];
		
		System.arraycopy(A, 1, B, 2, 3);
		
		for(int i = 0; i < B.length; i++)
		{
			System.out.println(B[i]);
		}
		
	}

}

// 0,0,0,0,0,0,0,0,0,0
// 0,1,2,3,4 index
// 1,2,3,4,5 position
// 2,3,4
// 0,0,2,3,4,0,0,0,0,0