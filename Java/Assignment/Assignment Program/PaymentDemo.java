package com.example;

/*Lab 10.3: Implement an interface for a real-world example, such as a payment gateway.*/

interface PaymentGateway
{
	void pay(double amount);
}

class CreditCardPayment implements PaymentGateway
{
	@Override
	public void pay(double amount) 
	{
		System.out.println("Paid ₹" + amount + " using Credit Card");
	}	
}

class UPIPayment implements PaymentGateway
{
	@Override
	public void pay(double amount)
	{
		System.out.println("Paid ₹" + amount + " using UPI");	
	}
}

public class PaymentDemo 
{
	public static void main(String[] args) 
	{
//		PaymentGateway p = new CreditCardPayment();
//		p.pay(2500.00);
//		
//		PaymentGateway p1 = new UPIPayment();
//		p1.pay(1500.00);
		
//		OR
		
		PaymentGateway p;
		p = new CreditCardPayment();
		p.pay(2500.00);
		 
		p = new UPIPayment();
		p.pay(1500.00);
		
	}

}
