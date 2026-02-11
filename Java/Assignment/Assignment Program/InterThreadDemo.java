package com.example;

/*Lab 13.3 Use inter-thread communication methods like wait(), notify(), and
notifyAll().*/


// producer-consumer problem
class SharedResource
{
	int data;
	boolean available = false;
	
	synchronized void produce(int value)
	{
		if (available) 
		{
			try 
			{
				wait();
			} 
			catch (InterruptedException e) 
			{
				
			}
		}
		
		data = value;
		System.out.println("Produced: " + data);
		available = true;
		notify();
	}
	
	synchronized void consume()
	{
		if (!available) 
		{
			try 
			{
				wait();
			} 
			catch (InterruptedException e) 
			{
				
			}
		}
		System.out.println("Consumed: " + data);
		available = false;
		notify();
	}
}

// producer thread
class Producer extends Thread
{
	SharedResource resource;
	
	Producer(SharedResource r)
	{
		resource = r;
	}
	
	public void run() 
	{
		for (int i = 1; i <= 5; i++) 
		{
			resource.produce(i);
		}
	}
}

// Consumer thread
class Consumer extends Thread
{
	SharedResource resource;
	
	Consumer(SharedResource r)
	{
		resource = r;
	}
	
	public void run() 
	{
		for (int i = 1; i <= 5; i++) 
		{
			resource.consume();
		}
	}
}

public class InterThreadDemo 
{
	public static void main(String[] args) 
	{
		SharedResource r = new SharedResource();
		
		Producer p = new Producer(r);
		Consumer c = new Consumer(r);
		
		p.start();
		c.start();
	}

}
