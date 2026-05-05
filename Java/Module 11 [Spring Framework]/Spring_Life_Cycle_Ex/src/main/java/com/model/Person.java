package com.model;

public class Person 
{
	int id;
	String name;
	
	public void myinit()
	{
		System.out.println("Initialized");
	}
	public void mydestroy()
	{
		System.out.println("Destroyed");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
