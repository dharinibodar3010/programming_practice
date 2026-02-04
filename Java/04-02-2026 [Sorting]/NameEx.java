package com.feb04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

class MyStudent
{  
	String name;  
 
	MyStudent(String name)
	{  
		this.name=name; 
	}  
} 

class NameComparator implements Comparator<MyStudent>
{  
	@Override
	public int compare(MyStudent o1, MyStudent o2) 
	{
		MyStudent s1=(MyStudent)o1;  
		MyStudent s2=(MyStudent)o2;  
  
		return s1.name.compareTo(s2.name);  
	}  
}

public class NameEx 
{
	public static void main(String[] args) 
	{
		ArrayList al = new ArrayList();  
		al.add(new MyStudent("Vijay"));  
		al.add(new MyStudent("Ajay"));  
		al.add(new MyStudent("Jai"));  
		
		Collections.sort(al,new NameComparator());  
		
		Iterator i = al.iterator();  
		
		while(i.hasNext())
		{
			MyStudent st=(MyStudent)i.next();  
			System.out.println(st.name);  
		}
		  
	}
}
