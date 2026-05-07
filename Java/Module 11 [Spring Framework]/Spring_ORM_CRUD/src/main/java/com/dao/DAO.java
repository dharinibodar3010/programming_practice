package com.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.model.Person;

public class DAO extends HibernateDaoSupport
{
	public void Insert(Person p)
	{
		this.getHibernateTemplate().save(p);
	}
	public void Update(Person p)
	{
		this.getHibernateTemplate().update(p);
	}
	public void Delete(Person p)
	{
		this.getHibernateTemplate().delete(p);
	}
	public List<Person> getall()
	{
		List<Person> getlist = (List<Person>) this.getHibernateTemplate().find("from Person");
		return getlist;
	}
	public Person getSingle(Person p)
	{
		Person p1 = this.getHibernateTemplate().get(Person.class, p.getId());
		return p1;
	}
}