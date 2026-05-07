package com.bo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.DAO;
import com.model.Person;

@Transactional
public class BO {
	DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public void Insert(Person p) {
		dao.Insert(p);
	}

	public void Update(Person p) {
		dao.Update(p);
	}

	public void Delete(Person p) {
		dao.Delete(p);
	}

	public Person getOne(Person p) {
		Person p1 = this.dao.getSingle(p);
		return p1;
	}

	public List<Person> ViewRecords() {
		List<Person> getlist = this.dao.getall();
		return getlist;
	}

}