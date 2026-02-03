package com.feb03022026;

import java.util.ArrayList;
import java.util.Iterator;

public class ListEx3 
{
	public static void main(String[] args) 
	{
		ArrayList arraylist = new ArrayList();
		
		arraylist.add("Java");
		arraylist.add("Php");
		arraylist.add("Python");
		arraylist.add("Spring");
		arraylist.add("Java");
		
		Iterator<String> i = arraylist.iterator();
		
		while (i.hasNext()) 
		{
			System.out.println(i.next());
		}
	
	}

}
