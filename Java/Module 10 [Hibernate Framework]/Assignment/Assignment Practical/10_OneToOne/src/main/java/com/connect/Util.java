package com.connect;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Profile;
import com.model.User;

public class Util 
{
	Session sess = null;
	
	public Session getconnect()
	{
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.password", "");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.dialect.storage_engine", "innodb");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		SessionFactory sf = new Configuration()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Profile.class)
				.addProperties(properties)
				.buildSessionFactory();

		sess = sf.openSession();
		
		return sess;
	}
}