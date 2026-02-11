package com.example;

/*Lab 4.3: Implement a simple class with getters and setters for encapsulation.*/

// PART : 2

public class TestStudent 
{
	public static void main(String[] args) 
	{
		Model m = new Model();
		
		m.setId(101);
		m.setName("Dharini");
		
		System.out.println("Id: " + m.getId());
		System.out.println("Name: " + m.getName());
		
	}

}
