package com.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.model.Student;

public class StudentDAO {
	
	HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	@Transactional
	public void insert(Student s) {
		
		template.save(s);
	}
	
	public List<Student> getAllStudents()
	{
		return template.loadAll(Student.class);
	}
	
	public Student getStudentById(int id)
	{
		return template.get(Student.class, id);
	}
	
	@Transactional
	public void update(Student s)
	{
		template.update(s);
	}
	
	@Transactional
	public void delete(int id)
	{
		Student s = template.get(Student.class, id);
		
		template.delete(s);
	}

}
