package com.controller;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.connect.Util;
import com.model.Profile;
import com.model.User;

public class MainClass 
{
	public static void main(String[] args)
	{
		Session sess =  new Util().getconnect();
		Transaction tr = sess.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your Name: ");
		String name = sc.next();
		
		System.out.println("Enter Your Address: ");
		String address = sc.next();
		
		System.out.println("Enter Your Phone No.: ");
		String phone = sc.next();
		
		User u = new User();
		u.setName(name);
		
		Profile p = new Profile();
		p.setAddress(address);
		p.setPhone(phone);
		
		p.setUser(u);
		
		sess.save(u);
		sess.save(p);
		
		tr.commit();
		sess.close();
	}
}
