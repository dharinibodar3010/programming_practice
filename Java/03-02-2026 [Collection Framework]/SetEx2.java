package com.feb03022026;

import java.util.HashSet;
import java.util.Iterator;

public class SetEx2 
{
	public static void main(String[] args) 
	{
		HashSet set2 = new HashSet();	// numerical -> sequention, alphabatical -> hash value
		
		set2.add("104");
		set2.add("102");
		set2.add("101");
		set2.add("105");
		set2.add("103");
		
		Iterator<String> i = set2.iterator();
		while (i.hasNext()) 
		{
			System.out.println(i.next());
		}
		
	}

}
