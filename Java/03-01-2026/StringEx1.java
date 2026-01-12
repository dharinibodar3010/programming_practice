package com.jan03012026;

public class StringEx1 
{
	public static void main(String[] args) 
	{
		String s = "tops";
		System.out.println(s);
		
		String s1 = new String("tops");
		System.out.println(s1);
		
		char ch[] = {'t', 'o', 'p', 's'};
		String s2 = new String(ch);
		System.out.println(s2);
	}

}
