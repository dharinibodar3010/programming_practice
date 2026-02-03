package com.feb03022026;

import java.util.ArrayList;
import java.util.Iterator;

public class ListEx1 
{
	public static void main(String[] args) 
	{
		ArrayList al = new ArrayList();
		
//		add
		al.add("Java");
		al.add("Php");
		al.addLast("Test2");
		al.add("Python");
		al.add("Spring");
		al.addFirst("Test1");
		
//		al.add(1);
//		System.out.println(al.get(0));	// -> index-based data access thay
		
//		System.out.println(al); 	// -> array ma data aave
		
		ArrayList al2 = new ArrayList();	// -> all data aave al and al2
		
		al2.add("Android");
		al2.add("Ios");
		al2.add("Flutter");
		al2.add("Java");
		
//		al.addAll(al2);
//		al.remove("Ios");
//		al.removeAll(al2);
		
//		al.retainAll(al2);
		
		
		Iterator<String> i = al.iterator();	 	// data sequence order ma aave che
		
		while (i.hasNext()) 
		{
			System.out.println(i.next());
		}
		
//		Iterator<String> i2 = al.iterator();	 	// data sequence order ma aave che
//		
//		while (i2.hasNext()) 
//		{
//			System.out.println(i2.next());
//		}
		
	}

}
