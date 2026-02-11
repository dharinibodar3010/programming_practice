package com.example;

/*Lab 4.3: Implement a simple class with getters and setters for encapsulation.*/

//PART : 1

class Model
{
	//private data members
	private int id;
	private String name;
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
}