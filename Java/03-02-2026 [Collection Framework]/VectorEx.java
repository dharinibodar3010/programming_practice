package com.feb03022026;

import java.util.Vector;

public class VectorEx 
{
	public static void main(String[] args) 
	{
		Vector v = new Vector(5);
		
		v.addElement("a");
		v.addElement("b");
		v.addElement("c");
		v.addElement("d");
		v.addElement("e");
		v.addElement("f");
		v.addElement("g");
		v.addElement("a");
		v.addElement("f");
		v.addElement("g");
		v.addElement("a");
		
		System.out.println(v);
		
		System.out.println("Size: " + v.size());
		System.out.println("Capacity: " + v.capacity());
		System.out.println(v.contains("a"));
		v.clear();
		
	}

}
