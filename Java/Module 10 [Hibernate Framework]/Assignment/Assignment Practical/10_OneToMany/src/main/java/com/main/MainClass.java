package com.main;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.model.Author;
import com.model.Book;

public class MainClass {

	public static void main(String[] args) {

		// Step 1: Load Configuration
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		// Step 2: Create Metadata
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		// Step 3: Create SessionFactory
		SessionFactory factory = meta.getSessionFactoryBuilder().build();

		// Step 4: Open Session
		Session sess = factory.openSession();

		// Step 5: Begin Transaction
		Transaction t = sess.beginTransaction();

		 // ===== Insert Data (Author 1) =====
        Author a1 = new Author();
        a1.setName("Chetan Bhagat");

        Book b1 = new Book();
        b1.setTitle("2 States");

        Book b2 = new Book();
        b2.setTitle("Half Girlfriend");

        // relation set
        b1.setAuthor(a1);
        b2.setAuthor(a1);

        ArrayList<Book> list1 = new ArrayList<Book>();
        list1.add(b1);
        list1.add(b2);

        a1.setBooks(list1);

        // ===== Insert Data (Author 2) =====
        Author a2 = new Author();
        a2.setName("James Gosling");

        Book b3 = new Book();
        b3.setTitle("Java Basics");

        Book b4 = new Book();
        b4.setTitle("Advanced Java");

        // relation set
        b3.setAuthor(a2);
        b4.setAuthor(a2);

        ArrayList<Book> list2 = new ArrayList<Book>();
        list2.add(b3);
        list2.add(b4);

        a2.setBooks(list2);

        // ===== Save =====
        sess.persist(a1); //persist - object ne DB ma save kare
        sess.persist(a2);

        t.commit();
        sess.close();

        System.out.println("Success");
	}

}
