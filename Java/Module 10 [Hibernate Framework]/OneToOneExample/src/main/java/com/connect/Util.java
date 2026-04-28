package com.connect;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Address;
import com.model.Person;

public class Util 
{
    private static SessionFactory sf;

    static {
        try {
            Properties properties = new Properties();

            // ✅ Updated Driver
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/crud?useSSL=false&serverTimezone=UTC");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.connection.password", "");

            // ✅ Correct Dialect
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.format_sql", "true");

            // ✅ Table handling
            properties.setProperty("hibernate.hbm2ddl.auto", "update");

            sf = new Configuration()
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Address.class)
                    .addProperties(properties)
                    .buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getconnect()
    {
        return sf.openSession();
    }
}