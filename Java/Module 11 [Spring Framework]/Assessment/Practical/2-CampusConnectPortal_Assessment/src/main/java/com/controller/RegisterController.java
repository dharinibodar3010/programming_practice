package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Student;
import com.repository.StudentRepository;

@Controller
public class RegisterController {

	@Autowired
	StudentRepository repo;

	@GetMapping("/register")
	public String registerPage() {

		return "register";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(Student s) {

		repo.save(s);

		return "login";
	}
}
