package com.feb04;

import java.util.ArrayList;
import java.util.Collections;

class Student implements Comparable<Student>
{
	int sid;
	String sname;
	
	public Student(int id,String name) 
	{
		sid = id;
		sname = name;
	}
	
	@Override
	public int compareTo(Student o) 
	{
		int value = 0;
		
		if(this.sid>o.sid)
		{
			return 1;
		}
		else if(this.sid<o.sid)
		{
			return -1;
		}
		else if(this.sid==o.sid)
		{
			return 0;
		}
		
		return value;
	}
}

public class ComparableEx 
{
	public static void main(String[] args) 
	{
		Student s1 = new Student(23, "a");
		Student s2 = new Student(56, "b");
		Student s3 = new Student(12, "c");
		Student s4 = new Student(45, "d");
		Student s5 = new Student(38, "e");
	
		ArrayList<Student>al = new ArrayList<Student>();
		
		al.add(s1);
		al.add(s2);
		al.add(s3);
		al.add(s4);
		al.add(s5);
		
		Collections.sort(al);
		
		for(Student s : al)
		{
			System.out.println(s.sid+" "+s.sname);
		}
		
	}
}
