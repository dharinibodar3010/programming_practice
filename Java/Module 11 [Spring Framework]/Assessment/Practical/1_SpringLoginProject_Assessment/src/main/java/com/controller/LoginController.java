package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserDAO;
import com.model.User;

@Controller
public class LoginController {

	@Autowired
	UserDAO userDAO;

	// Open Index Page

	@RequestMapping("/")
	public String homePage() {

		return "index";
	}

	// Open Login Page

	@RequestMapping("/loginPage")
	public String openLoginPage(Model model) {

		model.addAttribute("user", new User());

		return "login";
	}

	// Login Validation

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public String login(@ModelAttribute("user") User user, Model model) {

		boolean status = userDAO.validateUser(user.getUsername(), user.getPassword());

		if (status) {

			model.addAttribute("name", user.getUsername());

			return "dashboard";
		}

		else {

			throw new RuntimeException("Invalid Username or Password");
		}
	}

	// Error Handling

	@ExceptionHandler(RuntimeException.class)

	public String handleError(RuntimeException ex, Model model) {

		model.addAttribute("msg", ex.getMessage());

		return "error";
	}

}