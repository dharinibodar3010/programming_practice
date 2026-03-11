package com.dec25122025;

import java.util.Scanner;

public class LoginCheck {
	public static void main(String[] args) {
				
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Email: ");
		String email = sc.next();
		
		System.out.println("Enter Password: ");
		String password = sc.next();
		
		if(email.equals("tops@gmail.com") && password.equals("1234")) 
		{
			System.out.println("Login Successful");
		}
		else
		{
			System.out.println("Invalid Email or Password");
		}
			
		sc.close();
	}
}