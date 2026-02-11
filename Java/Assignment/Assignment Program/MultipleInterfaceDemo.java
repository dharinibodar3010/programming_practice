package com.example;

/*Lab 10.2: Write a program that implements multiple interfaces in a single class.*/

interface Printable
{
	void print();
}

interface Scannable
{
	void scan();
}

class MultiFunction implements Printable, Scannable
{

	@Override
	public void scan() 
	{
		System.out.println("Printing documents...");
	}

	@Override
	public void print() 
	{
		System.out.println("Scanning documents...");
	}
}

public class MultipleInterfaceDemo 
{
	public static void main(String[] args) 
	{
		MultiFunction m = new MultiFunction();
		
		m.print();
		m.scan();
	}

}
