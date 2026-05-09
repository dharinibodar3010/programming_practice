package com.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainClass {
	
	public static void main(String[] args) {
		
		ApplicationContext con = new ClassPathXmlApplicationContext("abc.xml");
		Person per = (Person) con.getBean("t1");
		System.out.println(per.getId()+":"+per.getName());
		
		Person per1 = (Person) con.getBean("t1");
		
		per.setId(102);
		per.setName("Test");
		
		System.out.println(per.getId()+":"+per.getName());
		System.out.println(per1.getId()+":"+per1.getName());

	}

}
