package com.dec27122025;

import java.util.Scanner;

public class Task {
	
	int id;
	String name;

	Task(int id, String name) 
	{
		this.id = id;
		this.name = name;
	}

	void display() 
	{
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Your ID: ");
		int id = sc.nextInt();
		
		System.out.print("Enter Your Name: ");
		String name = sc.next();
		
		Task t1 = new Task(id, name);
		t1.display();
		
		sc.close();
	}
}
