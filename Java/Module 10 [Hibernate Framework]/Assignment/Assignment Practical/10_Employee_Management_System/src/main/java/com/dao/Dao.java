package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Employee;
import com.util.Util;

public class Dao {
	
//	INSERT
	public void insert(Employee e) {

		Session s = Util.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();

		s.save(e);
		t.commit();
		s.close();

	}
	
//	VIEW ALL 
	public List<Employee> getAll() {
		
		Session s = Util.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		List<Employee> list = s.createQuery("from Employee", Employee.class).list();
		s.close();
		return list;
		
	}
	
//	UPDATE
	public void update(int id, double salary) {
		
		Session s = Util.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		Employee e = s.get(Employee.class, id);
		e.setSalary(salary);
		s.update(e);
		t.commit();
		s.close();
	}
	
//	DELETE
	public void delete(int id) {
		
		Session s = Util.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		Employee e = s.get(Employee.class, id);
		
		s.delete(e);
		t.commit();
		s.close();
	}

}
