package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Student;
import com.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	StudentRepository repo;

	@GetMapping("/")
	public String homePage() {

		return "index";
	}

	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

	@PostMapping("/loginStudent")
	public String loginStudent(
			@RequestParam String email,
			@RequestParam String password,
			HttpSession session) {

		Student s = repo.findByEmailAndPassword(email, password);

		if(s != null) {

			session.setAttribute("student", s);

			return "redirect:/dashboard";
		}

		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/login";
	}
}
