package com.dec30122025;

interface Myprint
{
	void print();
}

interface Mysave
{
	void save();
}


public class MultipleInheritance implements Myprint, Mysave
{

	@Override
	public void save() 
	{
		System.out.println("Saving...");
	}

	@Override
	public void print() 
	{
		System.out.println("Printing...");
	}	
	
	public static void main(String[] args) 
	{
		MultipleInheritance m = new MultipleInheritance();
		
		m.print();
		m.save();	
	}
}
