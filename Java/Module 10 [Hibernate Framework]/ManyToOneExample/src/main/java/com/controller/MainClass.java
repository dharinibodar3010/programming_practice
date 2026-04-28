package com.controller;

import java.util.ArrayList;
import java.util.List;
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
		
		System.out.println("Enter Your 1st Address");
		String add1 = sc.next();
		
		System.out.println("Enter Your 2nd Address");
		String add2 = sc.next();
		
		System.out.println("Enter Your Name");
		String name = sc.next();
		
		Person p = new Person();
		Address a1 = new Address();
		Address a2 = new Address();
		
		List<Address>list = new ArrayList<Address>();
		list.add(a1);
		list.add(a2);
		
		p.setName(name);
		p.setAddress(list);
		a1.setPerson(p);
		a2.setPerson(p);
		a1.setCity(add1);
		a2.setCity(add2);
		sess.save(p);
		sess.save(a1);
		sess.save(a2);
		tr.commit();
		sess.close();
		
	}
}