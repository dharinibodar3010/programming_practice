package com.mainclass;

import com.model.Student;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainClass {
    public static void main(String[] args) {

        // Scanner object banavyo user input mate
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        // Hibernate configuration
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        // Student object
        Student s = new Student();

        // user input set kariye che
        s.setName(name);
        s.setCity(city);

        // save to database
        session.save(s);

        session.getTransaction().commit();
        session.close();
        factory.close();

        // scanner close
        sc.close();

        System.out.println("Success");
    }
}