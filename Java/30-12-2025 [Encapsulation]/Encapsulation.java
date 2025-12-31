package com.dec30122025;

//PART : 2
public class Encapsulation 
{
	public static void main(String[] args) 
	{
		Model m = new Model();
		
		m.setName("Dharini");
		m.setSurname("Bodar");
		m.setCity("Rajkot");
		
		System.out.println(m.getName()+" from "+m.getCity());
		
	}

}
