package com.jan03012026;

public class StringEx3 
{
	public static void main(String[] args) 
	{
		String s1 = "sachin";
		String s2 = "sachin";
		String s3 = "ratan";
		String s4 = new String("sachin");
		String s5 = "SACHIN";
		String s6 = "dharini";
		
//		equals
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s4));
	    System.out.println(s1.equals(s3));
	    
//	    equalsIgnoreCase
	    System.out.println(s1.equals(s5));
	    System.out.println(s1.equalsIgnoreCase(s5));
	    
//	    compareTo
	    System.out.println(s1.compareTo(s2));
	    System.out.println(s1.compareTo(s3));
	    System.out.println(s1.compareTo(s6));
	    System.out.println(s1.compareToIgnoreCase(s5));
	    
//	    ==
	    System.out.println(s1 == s2);
	    System.out.println(s1 == s4); // memory address is different because used new keyword
	}

}
