package com.jan08012026;

public class ExceptionEx1 
{
	public static void main(String[] args) 
	{
		try
		{
			int data = 10/0;
			System.out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println(e);
		}
		finally
		{
			System.out.println("Executed");
		}
	}
}
