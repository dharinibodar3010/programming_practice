package com.model;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
public static void main(String[] args) {
	ApplicationContext con = new ClassPathXmlApplicationContext("abc.xml");
	Person per = (Person) con.getBean("t1");
	
	System.out.println("----LIST----");
	List<String> list = per.getList();
	for (String string : list) 
	{
		System.out.println(string);
	}
	System.out.println("----SET-----");
	Set<Integer> set = per.getSet();
	for (Integer integer : set) {
		System.out.println(integer);
	}
	System.out.println("-----MAP-----");
	System.out.println(per.getMap().get(101));
	
	System.out.println("----PROPERTIES------");
	System.out.println(per.getProperties().getProperty("hibernate.dialect"));
 }
}