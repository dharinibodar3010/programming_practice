package com.feb03022026;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class SetEx1 
{
	public static void main(String[] args) 
	{
		LinkedHashSet set1 = new LinkedHashSet();
		
		set1.add("Java");
		set1.add("Php");
		set1.add("Python");
		set1.add("Spring");
		set1.add("Java");
		
		Iterator<String> i = set1.iterator();
		
		while (i.hasNext()) 
		{
			System.out.println(i.next());
		}
	}

}
