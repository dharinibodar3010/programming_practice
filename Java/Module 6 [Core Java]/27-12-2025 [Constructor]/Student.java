package com.dec27122025;

public class Student {

	int id;
	String name;
	
	public static void main(String[] args) {
		
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = new Student();
		Student s4 = new Student();
		
		s1.id = 101;
		s1.name = "tops1";
		
		s2.id = 102;
		s2.name = "tops2";
		
		s3.id = 103;
		s3.name = "tops3";
		
		s4.id = 104;
		s4.name = "tops4";
		
		System.out.println(s1.id+" "+s1.name);
		System.out.println(s2.id+" "+s2.name);
		System.out.println(s3.id+" "+s3.name);
		System.out.println(s4.id+" "+s4.name);
	}
}
