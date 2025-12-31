package com.dec30122025;

class Bank
{
	void bank()
	{
		System.out.println("Banking");
	}
}

class Current extends Bank
{
	void current()
	{
		System.out.println("Current");
	}
}

class Saving extends Bank
{
	void saving()
	{
		System.out.println("Saving");
	}
}

public class HierarchicalInheritance 
{
	public static void main(String[] args) 
	{
		Current c = new Current();
		Saving s = new Saving();
		
		c.bank();
		c.current();
		s.saving();
	}

}
