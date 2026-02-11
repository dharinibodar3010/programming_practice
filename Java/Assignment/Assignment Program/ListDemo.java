package com.example;

/*Lab 15.1: Write a program that demonstrates the use of an ArrayList and LinkedList.*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ListDemo 
{
	public static void main(String[] args) 
	{
//		1) ArrayList Example
		ArrayList<String> al = new ArrayList<String>();
		
		al.add("Java");
		al.add("Python");
		al.add("Php");
		
		System.out.println("ArrayList Elements: ");
		
		Iterator<String> i = al.iterator();
		
		while (i.hasNext())
		{
			System.out.println(i.next());
		}
		
//		for (String s : al)
//		{
//			System.out.println(s);
//		}
		
		
//		2) LinkedList Example
		LinkedList<String> l = new LinkedList<String>();
		
		l.add("Ios");
		l.add("Flutter");
		l.add("Android");
		
		System.out.println("\nLinkedList Elements: ");
		
		Iterator<String> i1 = l.iterator();
		
		while (i1.hasNext())
		{
			System.out.println(i1.next());
		}
		
//		for (String s : l)
//		{
//			System.out.println(s);
//		}
	}

}
