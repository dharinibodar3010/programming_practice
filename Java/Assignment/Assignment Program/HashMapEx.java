package com.example;

import java.util.HashMap;
import java.util.Map;

/*Lab 15.3: Create a HashMap to store and retrieve key-value pairs.*/

public class HashMapEx 
{
	public static void main(String[] args) 
	{
//		Create HashMap
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
//		Store key-value pairs
		map.put(1, "Dharini");
		map.put(2, "Sweta");
		map.put(3, "Khushi");
		
//		Retrive value using key
		System.out.println("Value for key 1: " + map.get(1));
		System.out.println("Value for key 3: " + map.get(3));
		
//		Print all key-value pairs
		for (Map.Entry<Integer, String> entry : map.entrySet() )
		{
			System.out.println(entry.getKey() + " " + entry.getValue());
		}	
	}
}
