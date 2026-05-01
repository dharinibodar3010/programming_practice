package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.dao.Dao;
import com.model.Department;
import com.model.Employee;
import com.model.Profile;
import com.model.Project;

public class MainClass {
	
	public static void main(String[] args) {
		
		Dao dao = new Dao();
		
//		create department
		Department d = new Department();
		d.setName("IT");
		
//		create profile
		Profile p1 = new Profile();
		p1.setAddress("Rajkot");
		p1.setPhone("9874561230");
		
		Profile p2 = new Profile();
		p2.setAddress("Ahmedabad");
		p2.setPhone("9999999999");
		
//		project
		Project pr1 = new Project();
		pr1.setName("Project A");
		
		Project pr2 = new Project();
		pr2.setName("Project B");
		
//		employee
		Employee e1 = new Employee();
		e1.setName("Dharini");
		e1.setSalary(50000);
		e1.setProfile(p1);
		e1.setDepartment(d);
		
		Employee e2 = new Employee();
		e2.setName("Sweta");
		e2.setSalary(50000);
		e2.setProfile(p2);
		e2.setDepartment(d);
		
		ArrayList<Project> list = new ArrayList<Project>();
		list.add(pr1);
		list.add(pr2);
		
		e1.setProjects(list);
		e2.setProjects(list);
		
//		insert
		dao.insert(e1);
		dao.insert(e2);
		
//		update(only one employee)
		dao.update(1, 80000);   // ID 1 employee salary update
		
//		view
		List<Employee> empList = dao.getAll();
		
		for(Employee emp : empList) {
			System.out.println("ID: " + emp.getId());
			System.out.println("Name: " + emp.getName());
			System.out.println("Salary: " + emp.getSalary());
			System.out.println("Department: " + emp.getDepartment().getName());
			System.out.println("Profile Address: " + emp.getProfile().getAddress());
			
			for(Project pr : emp.getProjects()) {
				System.out.println("Project: " + pr.getName());
			}
		}		
		
//      delete (only one employee) 
		dao.delete(2);  //id 2 delete
		
	}
		
}