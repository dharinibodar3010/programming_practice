package com.jan10012026;

class A1 implements Runnable
{
	@Override
	public void run() 
	{
		for(int i = 1; i <= 10; i++)
		{
			System.out.println("A: " + i);
		}	
	}
}

class B1 implements Runnable
{
	@Override
	public void run() 
	{
		for(int i = 1; i <= 10; i++)
		{
			System.out.println("B: " + i);
		}
	}	
}

public class RunnableEx 
{
	public static void main(String[] args) 
	{
		A1 a = new A1();
		B1 b = new B1();
		
		Thread t1 = new Thread(new A1());
		Thread t2 = new Thread(new B1());
		
		t1.start();
		t2.start();
	}
}
