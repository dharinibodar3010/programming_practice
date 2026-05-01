package com.main;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.model.Course;
import com.model.Student;

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

        Transaction t = session.beginTransaction();

        // Courses
        Course c1 = new Course();
        c1.setName("Java");

        Course c2 = new Course();
        c2.setName("Python");

        // Student 1
        Student s1 = new Student();
        s1.setName("Dharini");

        ArrayList<Course> list1 = new ArrayList<>();
        list1.add(c1);
        list1.add(c2);

        s1.setCourses(list1);

        // Student 2
        Student s2 = new Student();
        s2.setName("Sweta");

        ArrayList<Course> list2 = new ArrayList<>();
        list2.add(c1); // same course shared
        list2.add(c2);

        s2.setCourses(list2);

        // Save
        session.persist(s1);
        session.persist(s2);

        t.commit();
        session.close();

        System.out.println("Success");
	}

}
