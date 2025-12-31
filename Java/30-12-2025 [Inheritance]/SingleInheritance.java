package com.dec30122025;

class A
{
	void a()
	{
		System.out.println("A Called");
	}
}

class B extends A
{
	void b()
	{
		System.out.println("B Called");
	}
}

public class SingleInheritance
{
	public static void main(String[] args) 
	{
		B b1 = new B();
		
		b1.a();
		b1.b();
		
	}
}