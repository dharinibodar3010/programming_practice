package com.jan29012026;

public class BankAccount 
{
	public static void main(String[] args) 
	{
		BankAccount ba = new BankAccount();
		CheckingAccount ca = new CheckingAccount(101);
		ca.deposite(50000);
		
		try 
		{
			ca.withdral(70000);
			System.out.println("Your balance is " + ca.getBalance());
		} 
		catch (InsufficientFundException e) 
		{
			System.out.println("You have no sufficient balance, You need more " + e.getAmount());
			System.out.println("Your balance is " + ca.getBalance());
		}
	}

}
