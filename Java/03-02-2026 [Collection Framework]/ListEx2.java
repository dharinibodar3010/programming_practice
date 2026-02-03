package com.feb03022026;

import java.util.Iterator;
import java.util.LinkedList;

public class ListEx2 
{
	public static void main(String[] args) 
	{
		LinkedList list = new LinkedList();
		
		list.add("a");
		list.add("b");
		list.add("c");
//		list.add();
		list.add("d");
		list.addFirst("e");
		
		System.out.println(list);
		
//		Iterator<String> i = list.iterator();
//		
//		while (i.hasNext()) 
//		{
//			System.out.println(i.next());
//			
//		}
	}

}
