package com.feb03022026;

import java.util.Stack;

public class StackEx 
{
	static void mypush(Stack st, Integer i) 
	{
		st.push(i);
		System.out.println(i);
		System.out.println(st);
	}
	
	static void mypop(Stack st)
	{
		Integer i = (Integer) st.pop();
		System.out.println(i);
		System.out.println(st);
	}
	
	public static void main(String[] args) 
	{
		Stack st = new Stack();
		
		mypush(st, 10);
		mypush(st, 20);
		mypush(st, 30);
		mypush(st, 40);
		
		try
		{
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
			mypop(st);
		}
		catch (Exception e)
		{
			System.out.println("No Data");
		}
		
	}

}
