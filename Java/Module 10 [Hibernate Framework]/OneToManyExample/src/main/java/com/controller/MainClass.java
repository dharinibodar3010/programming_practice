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
		
		System.out.println("Enter Your 1st Name");
		String name = sc.next();
		
		System.out.println("Enter Your 2nd Name");
		String name2 = sc.next();
		
		System.out.println("Enter Your Address");
		String add = sc.next();
		
		Address address = new Address();
		Person p = new Person();
		Person p2 = new Person();
		
		List<Person>list = new ArrayList<Person>();
		list.add(p);
		list.add(p2);
		address.setCity(add);
		p.setName(name);
		p2.setName(name2);
		address.setList(list);
		sess.save(p);
		sess.save(p2);
		sess.save(address);
		tr.commit();
		sess.close();
	
		
	}
}