package com.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.BO;
import com.model.Person;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext con = new ClassPathXmlApplicationContext("tops.xml");
		Scanner sc = new Scanner(System.in);
		BO bo = (BO) con.getBean("bo");
		Person p = (Person) con.getBean("model");

		// ----- INSERT -----
//	System.out.println("Enter name:");
//	p.setName(sc.next());
//	bo.Insert(p);

		// ----- UPDATE -----
//	System.out.println("Enter id:");
//	p.setId(sc.nextInt());
//	System.out.println("Enter name:");
//	p.setName(sc.next());
//	bo.Update(p);

		// ----- DELETE -----
	System.out.println("Enter id:");
	p.setId(sc.nextInt());
	bo.Delete(p);

		// ----- VIEW -----
//	List<Person> getall = bo.ViewRecords();
//	System.out.println("ID\t|\tName");
//	for (Person person : getall) 
//	{
//		System.out.println(person.getId()+"\t|\t"+person.getName());
//	}
	
		// ----- SINGLE -----
//	 System.out.println("Enter id:");
//	 p.setId(sc.nextInt());
//	 p = bo.getOne(p);
//	 System.out.println(p.getName());

	}
}