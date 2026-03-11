package com.dec27122025;

public class CopyConstructor {

	String name, surname, email;
	
	// Parameterized Constructor
	CopyConstructor(String n, String s, String e) {
		
		name = n;
		surname = s;
		email = e;		
	}
	
	// Copy Constructor
	CopyConstructor(CopyConstructor c1) {
		
		name = c1.name;
		surname = c1.surname;
		email = c1.email;
		
	}
	
	void display() {
		
		System.out.println(name+" "+surname+" "+email);
	}
	
	public static void main(String[] args) {
		
		CopyConstructor c1 = new CopyConstructor("dharini", "bodar", "dharinibodar3010@gmail.com");
		c1.display();
		
		// Copy Constructor called
		CopyConstructor c2 = new CopyConstructor(c1);
		c2.display();
		
	}

}
