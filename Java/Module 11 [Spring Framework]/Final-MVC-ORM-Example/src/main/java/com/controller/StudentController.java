package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.StudentDAO;
import com.model.Student;

@Controller
public class StudentController {
	
	@Autowired
	StudentDAO dao;

	public StudentDAO getDao() {
		return dao;
	}

	public void setDao(StudentDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/")
	public String home(Model m)
	{
		List<Student> list = dao.getAllStudents();

		System.out.println(list);

		m.addAttribute("list", list);
		return "index";
	}
	@RequestMapping("/add")

	public String addPage()
	{
		return "add";
	}
	@RequestMapping("/insert")

	public String insert(@ModelAttribute Student s)
	{
		dao.insert(s);

		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")

	public String edit(@PathVariable int id, Model m)
	{
		Student s = dao.getStudentById(id);

		m.addAttribute("student", s);

		return "edit";
	}

	@RequestMapping("/update")

	public String update(@ModelAttribute Student s)
	{
		dao.update(s);

		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")

	public String delete(@PathVariable int id)
	{
		dao.delete(id);

		return "redirect:/";
	}


}
