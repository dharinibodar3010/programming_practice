package com.dec30122025;

// final keyword is used to restrict modification. 
// A final variable cannot be changed.
// A final method cannot be overridden.
// A final class cannot be inherited.

final class G
{
	
}

public class FinalExample extends G
{

	public static void main(String[] args) 
	{
		final int speed = 90;
		speed = 120;
		
		System.out.println("Speed: " + speed);
	}

}
