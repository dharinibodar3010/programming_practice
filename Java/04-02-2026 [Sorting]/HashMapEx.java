package com.feb04;

import java.util.HashMap;
import java.util.Map;

public class HashMapEx 
{
	public static void main(String[] args)
	{
		HashMap<Integer,String> map = new HashMap();
		
		map.put(101,"khushi");
		map.put(102,"shweta");
		map.put(101,"dharini");
	
		for(Map.Entry m : map.entrySet())
		{
			System.out.println(m.getKey());
			System.out.println(m.getValue());
		}
		
	}

}
