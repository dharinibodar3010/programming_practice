package com.dec30122025;

/*super keyword is used to access parent class variables and methods 
when child class has the same members.*/

class Mycolor
{
	String color = "Black";
	
	void show()
	{
		System.out.println(color);
	}
}

class Mycolor2 extends Mycolor
{
	String color = "White";
	
	void show()
	{
		System.out.println(color);
		
		super.show();
	}
		
//	void display()
//	{
//		System.out.println("child color: " + color);
//		System.out.println("parent color: " + super.color);
//	}
	
}
public class SuperExample 
{

	public static void main(String[] args) 
	{
		Mycolor2 m = new Mycolor2();
		
		m.show();
		
	}

}
