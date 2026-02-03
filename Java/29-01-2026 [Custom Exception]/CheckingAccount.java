package com.jan29012026;

public class CheckingAccount 
{
	private int account;
	private double balance;
	
	public CheckingAccount(int account) 
	{
		this.account = account;
	}
	
	public void deposite(double amount) 
	{
		this.balance = amount;
	}
	
	public double getBalance() 
	{
		return balance;
	}
	
	public void withdral(double amount) throws InsufficientFundException
	{
		if (amount <= balance) 
		{
			balance = balance - amount;
		}
		else 
		{
			double needs = amount - balance;
			throw new InsufficientFundException(needs);
		}
	}

}
