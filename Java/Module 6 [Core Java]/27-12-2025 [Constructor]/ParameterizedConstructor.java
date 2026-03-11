package com.dec27122025;

public class ParameterizedConstructor {

	int id;
	String name;
	
	public ParameterizedConstructor(int i, String n) {
		
		id = i;
		name = n;
	}
	
	void display() {
		System.out.println(id+" "+name);
	}
	
	public static void main(String[] args) {
		
		ParameterizedConstructor p1 = new ParameterizedConstructor(101, "a");
		ParameterizedConstructor p2 = new ParameterizedConstructor(102, "b");
		ParameterizedConstructor p3 = new ParameterizedConstructor(103, "c");
		ParameterizedConstructor p4 = new ParameterizedConstructor(104, "d");
		
		p1.display();
		p2.display();
		p3.display();
		p4.display();
	}

}
