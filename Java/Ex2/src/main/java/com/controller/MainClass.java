package com.controller;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.connect.Util;
import com.model.Address;
import com.model.Person;

public class MainClass 
{
	public static void main(String[] args)
	{
		Session sess =  new Util().getconnect();
		Transaction tr = sess.beginTransaction();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your Name");
		String name = sc.next();
		
		System.out.println("Enter Your City");
		String city = sc.next();
		
		Person p = new Person();
		p.setName(name);
		
		Address a = new Address();
		a.setAddress(city);
		
		
		a.setPerson(p);
		
		sess.save(p);
		sess.save(a);
		tr.commit();
		sess.close();
		
	}
}
