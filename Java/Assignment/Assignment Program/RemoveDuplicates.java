package com.example;

/*Lab 15.2: Implement a program using HashSet to remove duplicate elements from a list.*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class RemoveDuplicates 
{
	public static void main(String[] args) 
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		al.add(20);
		al.add(50);
		al.add(40);
		al.add(80);
		al.add(10);
		al.add(20);
		
		System.out.println("Original list: ");
		System.out.println(al);
		
		HashSet<Integer> set = new HashSet<Integer>(al);	//Hashset removes duplicates value
		
		System.out.println("\nAfter removing duplicates: ");
		System.out.println(set);
		
//		Iterator<Integer> i = set.iterator();
//		while(i.hasNext())
//		{
//			System.out.println(i.next());
//		}
	}

}
