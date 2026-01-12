package com.jan03012026;

/*Task: Take user input and find the frequency of each character.*/
import java.util.Scanner;

public class CharFrequencyTask 
{
	public static void main(String[] args) 
	{
//		user input
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter String: ");
		String str = sc.next();
		
		System.out.println("Character frequencies: ");
		
//		outer loop
		for(int i = 0; i < str.length(); i++)
		{
			char ch = str.charAt(i);
			int count = 0;
			
//			count frequency
			for(int j = 0; j < str.length(); j++)
			{
				if(str.charAt(j) == ch)
				{
					count++;
				}
			}
			
//			check first occurrence
			boolean first = true;
			for(int k = 0; k < i; k++)
			{
				if(str.charAt(k) == ch)
				{
					first = false;
					break;
				}
			}
			
			if(first)
			{
				System.out.println(ch + " = " + count);
			}
		}
		
		sc.close();
	}

}
