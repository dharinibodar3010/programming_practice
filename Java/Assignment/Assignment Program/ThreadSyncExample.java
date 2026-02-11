package com.example;

/*Lab 13.2: Implement thread synchronization using synchronized blocks or methods.*/

class BankAccount
{
	private int balance = 1000;
	
//	Synchronized method
	public synchronized void deposit(int amount) 
	{
		balance += amount;
		
		System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", balance: " + balance);
	}
	
//	Synchronized block
	public void withdraw(int amount) 
	{
//		Only this block is synchronized
		synchronized(this)
		{
			if (balance >= amount) 
			{
				balance -= amount;
				
				System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", balance: " + balance);
			}
			else
			{
				System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + ", insufficient balance");
			}
		}
	}
	
	public int getBalance() 
	{
		return balance;
	}
}

class Customer extends Thread
{
	BankAccount account;
	boolean depositFlag;
	
	int amount;
	
	public Customer(BankAccount account, boolean depositFlag, int amount) 
	{
		this.account = account;
		this.depositFlag = depositFlag;
		this.amount = amount;
	}
	
	@Override
	public void run() 
	{
		if (depositFlag) 
		{
			account.deposit(amount);
		}
		else
		{
			account.withdraw(amount);
		}
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}

public class ThreadSyncExample 
{
	public static void main(String[] args)
	{
		BankAccount account = new BankAccount();
		
		Customer c1 = new Customer(account, true, 500);  // deposite 500
		Customer c2 = new Customer(account, false, 700); // withdraw 700
		Customer c3 = new Customer(account, false, 400); // withdraw 400
		
		c1.setName("Customer-1");
		c2.setName("Customer-2");
		c3.setName("Customer-3");
		
		c1.start();
		c2.start();
		c3.start();
	}
}
