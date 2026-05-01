package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.model.Employee;

public class MainClass {
	
	public static void main(String[] args) {
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata meta = new MetadataSources(ssr)
                .getMetadataBuilder()
                .build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        // ================= CREATE =================
        Transaction t1 = session.beginTransaction();

        Employee e = new Employee();
        e.setName("Rahul");
        e.setDepartment("IT");
        e.setSalary(50000);

        session.save(e);

        t1.commit();
        System.out.println("Inserted!");
        
     // ================= READ =================
        Transaction t2 = session.beginTransaction();

        Employee emp = session.get(Employee.class, 1);

        System.out.println("Employee: " + emp.getName());
        System.out.println("Dept: " + emp.getDepartment());
        System.out.println("Salary: " + emp.getSalary());

        t2.commit();
        
     // ================= UPDATE =================
        Transaction t3 = session.beginTransaction();

        Employee emp2 = session.get(Employee.class, 1);

        emp2.setName("Riya");        // name update
        emp2.setSalary(80000);      // salary update

        session.update(emp2);

        t3.commit();
        System.out.println("Updated!");
        
       // ================= DELETE =================
        Transaction t4 = session.beginTransaction();

        Employee emp3 = session.get(Employee.class, 1);
        session.delete(emp3);

        t4.commit();
        System.out.println("Deleted!");

        session.close();
        factory.close();

	}

}
