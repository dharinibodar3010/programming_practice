package com.feb04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Emp
{
	String ename;
	int salary;
	
	public Emp(String n,int s) 
	{
		ename = n;
		salary = s;
	}
}

class Salary implements Comparator<Emp>
{
	@Override
	public int compare(Emp o1, Emp o2) 
	{
		int value = 0;
		
		if(o1.salary>o2.salary)
		{
			return 1;
		}
		else if(o1.salary<o2.salary)
		{
			return -1;
		}
		else if(o1.salary==o2.salary)
		{
			return 0;
		}
		return value;
	}
}

public class ComparatorEx 
{
	public static void main(String[] args) 
	{
		Emp e1 = new Emp("a", 50000);
		Emp e2 = new Emp("b", 30000);
		Emp e3 = new Emp("c", 20000);
		Emp e4 = new Emp("d", 70000);
		
		ArrayList<Emp>al = new ArrayList<Emp>();
		al.add(e1);
		al.add(e2);
		al.add(e3);	
		al.add(e4);
		
		Collections.sort(al, new Salary());
		
		for(Emp e:al)
		{
			System.out.println(e.ename+" "+e.salary);
		}
	}
}
