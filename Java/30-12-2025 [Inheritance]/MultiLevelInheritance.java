package com.dec30122025;

class A1
{
	void a()
	{
		System.out.println("A Called");
	}
}

class B1 extends A1
{
	void b()
	{
		System.out.println("B Called");
	}
}

class C1 extends B1
{
	void c()
	{
		System.out.println("C Called");
	}
}

public class MultiLevelInheritance 
{
	public static void main(String[] args) 
	{
		C1 c1 = new C1();
		
		c1.a();
		c1.b();
		c1.c();
		
	}

}
